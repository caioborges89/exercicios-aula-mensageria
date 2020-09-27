package com.facef.kafka.contapalavras;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.facef.kafka.contapalvras.dto.WordCount;

@SpringBootApplication
public class ContapalavrasApplication {

	public static final int WINDOW_SIZE_MS = 30000;

	public static void main(String[] args) {
		SpringApplication.run(ContapalavrasApplication.class, args);
	}

	@Bean
	 public Function<KStream<Byte, String>, KStream<Bytes, WordCount>> process() {

		 return input -> input
	               .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
	               .map((key, value) -> new KeyValue<>(value, value))
	               .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
	               .windowedBy(TimeWindows.of(Duration.ofMillis(WINDOW_SIZE_MS)))
	               .count(Materialized.as("WordCounts-1"))
	               .toStream()
	               .map((key, value) -> new KeyValue<>(null, new WordCount(key.key(), value, new Date(key.window().start()), new Date(key.window().end()))));
	}

}
