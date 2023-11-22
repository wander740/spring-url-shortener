package com.example.demo.generateid;

import org.springframework.stereotype.Component;

import de.mkammerer.snowflakeid.SnowflakeIdGenerator;

@Component
public class GenerateAdapter implements GenerateId{
    SnowflakeIdGenerator generator = SnowflakeIdGenerator.createDefault(0);

    public GenerateAdapter(){ /*document why this constructor is empty */ }

    @Override
    public Long generate() {
        return generator.next();
    }
    
}
