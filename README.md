# Scala wordCount Program with exception handling
-------------------------------------------------------------

## Scala WordCount Program

### Create Spark configuration with master URL

```scala
val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local[*]")  // Use "local" for running locally or specify your Spark cluster URL
```    

### Create SparkContext using the configuration
   ```scala
 val sc = new SparkContext(conf)
```
### Read input text file into an RDD
   ```scala
val textFile = sc.textFile(inputPath)
```
 ### Perform word count
 ```scala
val wordCounts = textFile
        .flatMap(line => line.trim.split("\\s+"))  // Split each line into words
        .map(word => (word.toLowerCase, 1))        // Convert each word into a (word, 1) pair
        .reduceByKey(_ + _)                        // Reduce by key to count occurrences of each word
```


### Save the word counts to output directory
 ```scala
wordCounts.saveAsTextFile(outputPath)
```
   

### Stop SparkContext to release resources
 ```scala
 sc.stop()
```
      
