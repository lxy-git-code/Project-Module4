package com.cas;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author LiXingyu
 * @date 2020-05-23 9:52 上午
 */
public class BigFileReader {
    private int threadSize; //线程个数
    private String charset; //编码格式
    private int bufferSize;
    private IHandle handle;
    private ExecutorService  executorService;
    private long fileLength;
    private RandomAccessFile rAccessFile;
    private Set<StartEndPair> startEndPairs;
    private CyclicBarrier cyclicBarrier;
    private AtomicLong counter = new AtomicLong(0);

    private BigFileReader(File file, IHandle handle, String charset, int bufferSize, int threadSize){
        this.fileLength = file.length();
        this.handle = handle;
        this.charset = charset;
        this.bufferSize = bufferSize;
        this.threadSize = threadSize;
        try {
            this.rAccessFile = new RandomAccessFile(file,"r"); //关联文件只读
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.executorService = Executors.newFixedThreadPool(threadSize);
        startEndPairs = new HashSet<StartEndPair>();
    }

    public void start(){
        long everySize = this.fileLength/this.threadSize;
        try {
            calculateStartEnd(0, everySize);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        final long startTime = System.currentTimeMillis();
        cyclicBarrier = new CyclicBarrier(startEndPairs.size(),new Runnable() {

            @Override
            public void run() {
                System.out.println("use time: "+(System.currentTimeMillis()-startTime));
                System.out.println("all line: "+counter.get());
            }
        });
        for(StartEndPair pair:startEndPairs){
            System.out.println("分配分片："+pair);
            this.executorService.execute(new SliceReaderTask(pair));
        }
    }


    /**根据起始位置和每次读取字节数读取文件，形成文件块StartEndPair pair放入集合startEndPairs中
     *
     * @param start 起始位置
     * @param size 每次读取的字节数
     * @throws IOException
     */
    private void calculateStartEnd(long start,long size) throws IOException{
        if(start>=fileLength){
            return;
        }
        StartEndPair pair = new StartEndPair();
        pair.start=start;
        long endPosition = start+size;
        if(endPosition>=fileLength){
            pair.end=fileLength;
            startEndPairs.add(pair);
            return;
        }

        rAccessFile.seek(endPosition);
        byte tmp =(byte) rAccessFile.read();
        while(tmp!="\n".getBytes()[0]){
            endPosition++;
            if(endPosition>=fileLength){
                endPosition=fileLength;
                break;
            }
            rAccessFile.seek(endPosition);
            tmp =(byte) rAccessFile.read();
        }
        pair.end=endPosition;
        startEndPairs.add(pair);

        calculateStartEnd(endPosition+1, size);

    }



    public void shutdown(){
        try {
            this.rAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.executorService.shutdown();
    }
    private void handle(byte[] bytes) throws UnsupportedEncodingException {
        String line = null;
        if(this.charset==null){
            line = new String(bytes);
        }else{
            line = new String(bytes,charset);
        }
        if(line!=null && !"".equals(line)){
            this.handle.handle(line);
            counter.incrementAndGet();
        }
    }
    private static class StartEndPair{
        public long start;
        public long end;

        @Override
        public String toString() {
            return "star="+start+";end="+end;
        }

      /*  @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (end ^ (end >>> 32));
            result = prime * result + (int) (start ^ (start >>> 32));
            return result;
        }*/

      /*  @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            StartEndPair other = (StartEndPair) obj;
            if (end != other.end)
                return false;
            if (start != other.start)
                return false;
            return true;
        }*/

    }
    private class SliceReaderTask implements Runnable{
        private long start;
        private long sliceSize;
        private byte[] readBuff;

        public SliceReaderTask(StartEndPair pair) {
            this.start = pair.start;
            this.sliceSize = pair.end-pair.start;
            this.readBuff = new byte[bufferSize];
        }

        @Override
        public void run() {
            try {
                MappedByteBuffer mapBuffer = rAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY,start, this.sliceSize);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                for(int offset=0;offset<sliceSize;offset+=bufferSize){
                    int readLength;
                    if(offset+bufferSize<=sliceSize){
                        readLength = bufferSize;
                    }else{
                        readLength = (int) (sliceSize-offset);
                    }
                    mapBuffer.get(readBuff, 0, readLength);
                    for(int i=0;i<readLength;i++){
                        byte tmp = readBuff[i];
                        if(tmp=='\n'){
                            handle(bos.toByteArray());
                            bos.reset();
                        }else{
                            bos.write(tmp);
                        }
                    }
                }
                if(bos.size()>0){
                    handle(bos.toByteArray());
                }
                cyclicBarrier.await();//测试性能用
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static class Builder{
        private int threadSize=1;
        private String charset=null;
        private int bufferSize=1024*1024;
        private IHandle handle;
        private File file;
        public Builder(String file,IHandle handle){
            this.file = new File(file);
            if(!this.file.exists())
                throw new IllegalArgumentException("文件不存在！");
            this.handle = handle;
        }

        public Builder withTreahdSize(int size){
            this.threadSize = size;
            return this;
        }

        public Builder withCharset(String charset){
            this.charset= charset;
            return this;
        }

        public Builder withBufferSize(int bufferSize){
            this.bufferSize = bufferSize;
            return this;
        }

        public BigFileReader build(){
            return new BigFileReader(this.file,this.handle,this.charset,this.bufferSize,this.threadSize);
        }
    }

    public static void main(String[] args) {

            BigFileReader.Builder builder = new BigFileReader.Builder("/Users/lixingyu/Desktop/spring.txt",new IHandle() {

                @Override
                public void handle(String line) {
                   System.out.println(Thread.currentThread().getName()+":"+line);
                    //increat();
                }
            });
            builder.withTreahdSize(3)
                    .withCharset("GB18030")
                    .withBufferSize(1024*1024);
            BigFileReader bigFileReader = builder.build();
            bigFileReader.start();
        }
    }
