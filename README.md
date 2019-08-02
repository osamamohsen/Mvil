# Mvil
Mvil is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
Mvil primary focus is on making scrolling any kind of a list of images as smooth and fast as possible, but Glide is also effective for almost any case where you need to fetch, resize, and display a remote image.


# Miva Image Loader

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
