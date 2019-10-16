/*
 * 软件版权: 杭州瑞可科技有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/10/16  regtech  新增
 * ========    =======  ============================================
 */
package com.kjp;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 功能说明:
 * 开发人员: regtech regtech@hzregtech.com <br>
 * 开发时间: 2019/10/16 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public class TopicConsumer {
  public static void main(String[] args) {

    TopicConsumer consumer = new TopicConsumer();
    consumer.sendTopic();
  }

  public void sendTopic() {
    //定义链接工厂
    ConnectionFactory connectionFactory = null;

    //定义链接对象
    Connection connection = null;

    //定义会话
    Session session = null;

    //目的地
    Destination destination = null;

    //定义消息的消费者（接收者）
    MessageConsumer consumer = null;

    //定义消息
    Message message = null;


    try {
      /**
       * userName:访问ActiveMQ服务的用户名，默认为admin。
       * password：访问ActiveMQ服务的密码，默认为admin
       * 用户名和用户密码都可以通过ActiveMQ安装目录的oonf目录下的jetty-ream.properties文件进行修改
       *
       * borkerURL：访问ActiveMQ服务的路径地址。
       *  路径结构为：协议名://主机地址:端口号
       *  在conf/activemq.xml文件中可以找到修改
       *  在上一篇文章中都有介绍
       */
      connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

      //创建链接对象
      connection = connectionFactory.createConnection();

      //启动连接
      connection.start();

      /**
       * 参数一：transacted 是否使用事务 可选值为:true|false
       *      ture:使用事务 设置第二个参数变量这为 Session.SESSION_TRANSACTION 交由session管理
       *      false:不使用事务，则设置我们的参数即可
       *
       * acknowledgeMode:
       * * Session.AUTO_ACKNOWLEDGE:自动消息确认机制
       * * Session.CLIENT_ACKNOWLEDGE:客户端确认机制
       * * Session.DUPS_OK_ACKNOWLEDGE:有副本的客户端确认消息机制
       这里设置 这两个参数的含义为：
       不使用事务，并由Session自动确认提交
       * 这里对此不作过多讲解，初学者跟着敲，其后在慢慢了解原理
       *
       */
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      //创建目的地，目的地名称即队列的名称，也就是保存我们消息地方的名称。消息的消费者需要通过此名称访问对应的队列
      //名称不固定
      destination = session.createTopic("send-topic");

      //创建消息的消费者 需要指定目的地（也就是把Destination传入参数）
      consumer = session.createConsumer(destination);

      //创建消息对象 由消费者接收消息
      message = consumer.receive();

      //处理消息
      String msg = ((TextMessage) message).getText();
      System.out.println("ActiveMQ say:" + msg);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      //回收消息接收者资源
      if (consumer != null) {
        try {
          consumer.close();
        } catch (JMSException e) {
          e.printStackTrace();
        }
      }
      if (session != null) {
        try {
          session.close();
        } catch (JMSException e) {
          e.printStackTrace();
        }
      }
      if (connection != null) {
        try {
          connection.close();
        } catch (JMSException e) {
          e.printStackTrace();
        }
      }
    }
  }
}