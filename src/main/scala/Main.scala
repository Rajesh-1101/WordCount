import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    // Create Spark configuration with master URL
    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local[*]")  // Use "local" for running locally or specify your Spark cluster URL

    // Create SparkContext using the configuration
    val sc = new SparkContext(conf)

    try {
      // Read input text file into an RDD
      val inputPath = "C:\Users\zuran\IdeaProjects\Data\wordcount.txt"
      val textFile = sc.textFile(inputPath)

      // Perform word count
      val wordCounts = textFile
        .flatMap(line => line.trim.split("\\s+"))  // Split each line into words
        .map(word => (word.toLowerCase, 1))        // Convert each word into a (word, 1) pair
        .reduceByKey(_ + _)                        // Reduce by key to count occurrences of each word

      // Save the word counts to output directory
      val outputPath = "Output/wordcount_result"
      wordCounts.saveAsTextFile(outputPath)

      println(s"Word count completed successfully. Results saved in: $outputPath")
    } catch {
      case e: Exception => println(s"Error processing Spark job: ${e.getMessage}")
    } finally {
      // Stop SparkContext to release resources
      sc.stop()
    }
  }
}
