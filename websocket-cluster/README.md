#### spring-boot-starter-websocket redis
利用 <code>spring-boot-starter-websocket</code> 实现一个简单的智能人机对话。  
一个是基于浏览器支持的 WebSocket 实现的，页面是 templates/index.html.  
访问路径： http://localhost:8080   
一个是基于sockJs实现的， 页面是 templates/sockjs.html.  
访问路径: http://localhost:8080/sockjs    

业务简单思路:  
使用Map模拟简单的题库，接收问题后查找 Map.key ，如果能部分匹配上的话把 Map.value 作为答案返回，如果匹配不上的话则直接返回问答描述不正确。
如果客户端在一分钟后都没有继续提问，则服务端主动向客户端发送一条消息，并关闭连接。

## 新增点
* 这个比 websocket 多处理了一个分布式的问题  

如果同时部署多台服务的时候，服务端想给某个用户发送消息，但是这个时候不知道用户连的是哪一台服务的socket，而且 websocket session 不能像 httpsession 
一样做共享，所以需要通过中间服务处理。这里利用了 redis 的订阅服务。把需要发送给用户的消息发送给 redis 频道，多台服务都订阅该频道，
然后服务自已查看本地有没有该用户的 websocket session， 如果有则给该用户发送消息。

这里通过 controller 提供一个接口用于给用户发送消息，如果指定了 userId 则给指定用户发送，如果没有指定 userId 则给所有的用户发送.
  
http://localhost:8080/send?msg=收到&userId=1234