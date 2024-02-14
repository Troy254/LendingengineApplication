package com.peerlender.lendingengine.application.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

  private static final String TOPIC = "userRegisteredTopic";
  private static final String QUEUE_NAME = "user.registered";

  @Bean
  public Queue userRegisteredQueue() {

    return new Queue(QUEUE_NAME, false);
  }

  @Bean
  public TopicExchange userRegisteredTopic() {

    return new TopicExchange(TOPIC);
  }

  @Bean
  public Binding binding(Queue queue, TopicExchange topicExchange) {
    return BindingBuilder.bind(queue).to(topicExchange).with("user.#");
  }

  //add Listeners
  @Bean
  public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
      MessageListenerAdapter mesageListenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(QUEUE_NAME);
    container.setMessageListener(mesageListenerAdapter);
    return container;
  }

  @Bean
  public MessageListenerAdapter userRegisteredEventListener(){
    return new MessageListenerAdapter();
  }

}
