# Mvil
Mvil is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
Mvil primary focus is on making scrolling any kind of a list of images as smooth and fast as possible, but Glide is also effective for almost any case where you need to fetch, resize, and display a remote image.

Download
--------
For detailed instructions and requirements, see Glide's [download and setup docs page][28].

You can download a jar from GitHub's [releases page][1].

Or use Gradle:

```gradle
repositories {
  mavenCentral()
  google()
}

dependencies {
   implementation 'com.github.osamamohsen:Mvil:1.0'
}
```

Or Maven:

```xml
<dependency>
    <groupId>com.github.osamamohsen</groupId>
    <artifactId>Mvil</artifactId>
    <version>1.0</version>
</dependency>
<repositories>
     <repository>
         <id>jitpack.io</id>
         <url>https://jitpack.io</url>
     </repository>
</repositories>
```



How do I use Glide?
-------------------

#### Using Kotlin Extensions
``` kotlin
imageView.placeholder = resources.getDrawable(R.drawable.placeholder)
imageView.source = imageUrl // Url or drawable resource
```
#### Using Miva Builder
```java - kotlin
Miva.with(this)
     .placeholder(R.drawable.placeholder)
     .source(imageUrl)
     .loadImage(imageView)
```

##### Other options
```java - kotlin
Miva.with(this)
     .placeholder(R.drawable.placeholder)
     .resize(300, 300) // here resize image default (500 x 500)
     .disableCache() // Disable cache. By default its enabled -> enableCache(true)
     .setMaxCapacityCache(your_float_here) // default memoryClass * 1024 * 1024 as float
     .source(imageUrl)
     .loadImage(imageView)
