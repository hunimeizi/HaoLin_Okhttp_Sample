# HaoLin_Okhttp_Sample

## Okhttp 访问原理（请求队列 重试机制）

    ```java
     public class ThreadPoolManager {

         private static ThreadPoolManager threadPoolManager = new ThreadPoolManager();

         public static ThreadPoolManager getThreadPoolManager() {
             return threadPoolManager;
         }

         // 创建队列 该方法 就是 先进先出 形式
         private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<>();

         // 将异步任务添加到队列中
         public void addTask(Runnable runnable) {

             if (runnable != null) {
                 try {
                     mQueue.put(runnable);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }

         //创建线程池
         private ThreadPoolExecutor mThreadPoolExecutor;

         private ThreadPoolManager() {  // 3代表核心线程数 一般默认情况下不会被销毁
             //10 代表最大线程数
             //  保存时间
             //  时间单位
             mThreadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS,
                     new ArrayBlockingQueue<Runnable>(4),
                     new RejectedExecutionHandler() { // 线程池可能会在某种不确定下被关掉，导致失败  将该任务直接给抛出来
                         @Override
                         public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                             addTask(runnable);
                         }
                     });
             mThreadPoolExecutor.execute(coreThread);
             mThreadPoolExecutor.execute(delayThread);
         }

         //创建“叫号员” 让队列与线程池交互
         public Runnable coreThread = new Runnable() {
             Runnable run = null;

             @Override
             public void run() {
                 while (true) {
                     try {
                         run = mQueue.take();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     mThreadPoolExecutor.execute(run);
                 }
             }
         };

         //创建延迟队列  重试机制是要延迟一定的时间才会重试
         private DelayQueue<HttpTask> mDelayQueue = new DelayQueue<>();

         //将失败的httpTask 添加到延迟队列
         public void addDelayTask(HttpTask httpTask) {
             if (httpTask != null) {
                 httpTask.setDelayTime(3000);
                 mDelayQueue.offer(httpTask);
             }
         }

         //创建延迟线程 不停的从延迟队列中获取请求，并提交给线程池
         public Runnable delayThread = new Runnable() {
             HttpTask ht;

             @Override
             public void run() {
                 while (true) {
                     try {
                         ht = mDelayQueue.take();
                         if (ht.getRetryCount() < 3) {//判断请求三次不成功直接放弃
                             mThreadPoolExecutor.execute(ht);
                             ht.setRetryCount(ht.getRetryCount() + 1);
                             Log.e("lyb========", "重试机制" + "  " + ht.getRetryCount() + "  " + System.currentTimeMillis());
                         } else {
                             Log.e("lyb==========", "重试执行3次都不成功，直接放弃");
                         }
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }
         };
     }

    ```
