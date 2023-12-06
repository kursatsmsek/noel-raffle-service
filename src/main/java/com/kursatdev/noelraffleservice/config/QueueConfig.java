package com.kursatdev.noelraffleservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    @Value("${sr.rabbit.queue1.name}")
    private String queue1Name;

    @Value("${sr.rabbit.queue2.name}")
    private String queue2Name;

    @Value("${sr.rabbit.routing1.name}")
    private String routing1Name;

    @Value("${sr.rabbit.routing2.name}")
    private String routing2Name;

    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;

    @Bean
    public Queue queue1() {
        return new Queue(queue1Name, true);
    }

    @Bean
    public Queue queue2() {
        return new Queue(queue2Name, true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding1(final Queue queue1, final DirectExchange directExchange) {
        return BindingBuilder.bind(queue1).to(directExchange).with(routing1Name);
    }

    @Bean
    public Binding binding2(final Queue queue2, final DirectExchange directExchange) {
        return BindingBuilder.bind(queue2).to(directExchange).with(routing2Name);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}