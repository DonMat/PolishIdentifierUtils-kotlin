# Polish Identifiers Utils
[![](https://jitpack.io/v/DonMat/PolishIdentifiersUtils-kotlin.svg)](https://jitpack.io/#DonMat/PolishIdentifiersUtils-kotlin)
[![Build Status](https://travis-ci.org/DonMat/PolishIdentifiersUtils-kotlin.svg?branch=master)](https://travis-ci.org/DonMat/PolishIdentifiersUtils-kotlin)


This is simple lightweight library written in Kotlin to manage and validates polish identification numbers like: NIP, PESEL, REGON (9-digit and 14-digit).

## Purpose of project
It is easier to maintain validators and other utilities functions as one additional library instead of coppy&pase the same code between projects.


## Download
Gradle:

Step 1. Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
    repositories {
	...
	maven { url "https://jitpack.io" }
    }
}
```

Step 2. Add the dependency
```gradle
dependencies {
        compile 'com.github.DonMat:PolishIdentifiersUtils-kotlin:v0.0.2'
}
```

## The library contains
- PeselUtil
  * isValid()
  * getBirthDate()
  * getGender()

- NipUtil
  * isValid()

- RegonValidator
  * isValid()

License
-------
  Copyright (C) 2017 Mateusz Utkała

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.