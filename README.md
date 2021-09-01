# micronaut-recursive-update

Repository with a sample project to
reproduce `io.micronaut.http.client.exceptions.HttpClientResponseException: Internal Server Error: Error instantiating bean of type [<bean>]: Recursive update`
exception.

# How to reproduce?

```shell
while ./gradlew cleanTest :test; do :; done
```

After a few runs you should end up with a BUILD FAILED message and following exception in the test report:

```
io.micronaut.http.client.exceptions.HttpClientResponseException: Internal Server Error: Error instantiating bean of type [com.example.recursiveupdate.HostHeaderExtractorImpl]: Recursive update
	at app//io.micronaut.http.client.netty.DefaultHttpClient$11.channelReadInstrumented(DefaultHttpClient.java:2146)
	at app//io.micronaut.http.client.netty.DefaultHttpClient$11.channelReadInstrumented(DefaultHttpClient.java:2061)
	at app//io.micronaut.http.client.netty.DefaultHttpClient$SimpleChannelInboundHandlerInstrumented.channelRead0(DefaultHttpClient.java:2765)
	at app//io.netty.channel.SimpleChannelInboundHandler.channelRead(SimpleChannelInboundHandler.java:99)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:379)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:365)
	at app//io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:357)
	at app//io.micronaut.http.netty.stream.HttpStreamsHandler.channelRead(HttpStreamsHandler.java:194)
	at app//io.micronaut.http.netty.stream.HttpStreamsClientHandler.channelRead(HttpStreamsClientHandler.java:183)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:379)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:365)
	at app//io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:357)
	at app//io.netty.handler.codec.MessageToMessageDecoder.channelRead(MessageToMessageDecoder.java:103)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:379)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:365)
	at app//io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:357)
	at app//io.netty.handler.codec.MessageToMessageDecoder.channelRead(MessageToMessageDecoder.java:103)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:379)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:365)
	at app//io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:357)
	at app//io.netty.channel.CombinedChannelDuplexHandler$DelegatingChannelHandlerContext.fireChannelRead(CombinedChannelDuplexHandler.java:436)
	at app//io.netty.handler.codec.ByteToMessageDecoder.fireChannelRead(ByteToMessageDecoder.java:324)
	at app//io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:296)
	at app//io.netty.channel.CombinedChannelDuplexHandler.channelRead(CombinedChannelDuplexHandler.java:251)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:379)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:365)
	at app//io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:357)
	at app//io.netty.handler.timeout.IdleStateHandler.channelRead(IdleStateHandler.java:286)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:379)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:365)
	at app//io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:357)
	at app//io.netty.channel.DefaultChannelPipeline$HeadContext.channelRead(DefaultChannelPipeline.java:1410)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:379)
	at app//io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:365)
	at app//io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:919)
	at app//io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:166)
	at app//io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:719)
	at app//io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:655)
	at app//io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:581)
	at app//io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:493)
	at app//io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:986)
	at app//io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
	at app//io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.base@15.0.2/java.lang.Thread.run(Thread.java:832)
```

The exception occurs only intermittently. For this reason I can't provide a reprosteps with a guarantee. The project
might behave differently on different setups. On my machine with Windows the build while loop fails fairly quickly -
within 10 minutes.
