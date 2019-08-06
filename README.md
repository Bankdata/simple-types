# Overview

This project contains a number of simple types used by Bankdata financial APIs. The
project is intended to aid the implementation of Java POJO models for JSON 
serialization.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/dk.bankdata.api/types/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dk.bankdata.api/types/)
[![Javadoc](https://javadoc.io/badge/dk.bankdata.api/types/badge.svg)](https://www.javadoc.io/doc/dk.bankdata.api/types)
[![Build Status](https://travis-ci.com/Bankdata/simple-types.svg?branch=master)](https://travis-ci.com/Bankdata/simple-types)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Known Vulnerabilities](https://snyk.io/test/github/Bankdata/simple-types/badge.svg?targetFile=build.gradle)](https://snyk.io/test/github/Bankdata/simple-types?targetFile=build.gradle)

## Usage

The library is deployed to Maven Central `dk.bankdata.api:types` and may be used from both Gradle or Maven.

## AccountNumber
AccountNumber represents a danish account along with an encryption and decryption option.

```java
AccountNumber number = new AccountNumber.Builder()
    .regNo("some-regno")
    .accountNo("some-accountno")
    .shadowAccountId("some-shadowAccountId")
    .cipherKey("ThisIsPossiblyTheWorstCreatedKey")
    .build();

```

Which will be serialized as 

```json
{
  "regNo": "some-regno", 
  "accountNo": "some-accountno",
  "shadowAccountId": "some-shadowAccountId",
  "publicId": "gSM4IML5DaaCi3ctaFlP1jrTbpByjMGH9iD28Z96i4gOti8cx0tiaBNwJyDV-YHQj9GYU_OCMwvmh4t0gIv38PlXvMqlUbY7A4Zwan9EBhW_xOxtkZ3Zqneey0DXknf6qV8V-wBFGg5wT-GzHrRn7A=='
}
```

<b>NOTE</b>: a publicId will only be generated if a cipherKey has been supplied

To decrypt a publicId use the following method

```java
AccountNumber decrypted = AccountNumber
    .decrypt("ThisIsPossiblyTheWorstCreatedKey", 
              number.getPublicId()
);

```

## Problem Details

The library contains an implementation of [RFC 7807 - Problem Details for HTTP APIs](https://tools.ietf.org/html/rfc7807) which
can be used in responses of type `application/problem+json` as follows.

```java
ProblemDetails problem = new ProblemDetails.Builder()
    .type(URI.create("https://type"))
    .title("Error Occurred")
    .detail("An unrecoverable error occurred")
    .status(500)
    .instance(URI.create("https://instance/1"))
    .extensionMember("balance", 23)
    .build();
```

Which will be serialized as:

```json
{
  "type": "https://type",
  "title": "Error Occurred",
  "detail": "An unrecoverable error occurred",
  "status": 500,
  "instance": "https://instance/1",
  "balance": 23
}
```

_Note_ the use of extension members relies on Jackson serialization features.

## Amount with Currency

The library contains a simple type for representing an amount with associated
currency. Simply use `dk.bankdata.api.types.CurrencyAmount` which will be serialized
to json as:
```json
{
  "amount": 12345.12,
  "currency": "DKK"
}
```

## Date and Time Serialization

The library contains simple support for always serializing date and time into
and JSON object with milliseconds from unix epoch as well as a more human-readable
ISO formatted date. _Note_ this requires Jackson to be used for serialization.

The date and time will be serialized as follows.

```json
{
  "epochMilli": 3600000,
  "utc": "1970-01-01T01:00:00Z"
}
```

This may be achieved either by using `dk.bankdata.api.types.DateTime` or by using
a standard Java `java.time.Instant` adding a Jackson serializer like so.

```java
class POJO {
    @JsonSerialize(using = DateTimeSerializer.class)
    Instant dateTime;
}
```

