# Mvil
Mvil is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
Mvil primary focus is on making scrolling any kind of a list of images as smooth and fast as possible, but Mvil is also effective for almost any case where you need to fetch, resize, and display a remote image.

Download
--------

Or use Gradle:

```gradle
repositories {
  mavenCentral()
  google()
  maven { url 'https://jitpack.io' }
}

dependencies {
   implementation 'com.github.osamamohsen:Mvil:1.4'
   implementation 'org.jetbrains.anko:anko:0.10.5'
}
```

Or Maven:

```xml
<dependency>
    <groupId>com.github.osamamohsen</groupId>
    <artifactId>Mvil</artifactId>
    <version>1.4</version>
</dependency>
<repositories>
     <repository>
         <id>jitpack.io</id>
         <url>https://jitpack.io</url>
     </repository>
</repositories>
```



How do I use Mvil?
-------------------

#### Using Kotlin Extensions
``` kotlin
imageView.placeholder = resources.getDrawable(R.drawable.placeholder)
imageView.source = imageUrl // Url or drawable resource
```
#### Using Mvil Builder
``` kotlin
Mvil.with(this)
     .placeholder(R.drawable.placeholder)
     .source(imageUrl)
     .loadImage(imageView)
```

##### Other options
``` kotlin
Mvil.with(this)
     .placeholder(R.drawable.placeholder) // set in error loading
     .resize(300, 300) // here resize image default (500 x 500)
     .disableCache() // Disable cache. By default its enabled -> enableCache(true)
     .setMaxCapacityCache(your_float_here) // default memoryClass * 1024 * 1024 as float
     .source(imageUrl)
     .loadImage(imageView)
