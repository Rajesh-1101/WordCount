import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    
    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local[*]")  

    val sc = new SparkContext(conf)

    try {
      
      val inputPath = "C:/Users/zuran/IdeaProjects/Data/wordcount.txt"
      val textFile = sc.textFile(inputPath)

      val wordCounts = textFile
        .flatMap(line => line.trim.split("\\s+")) 
        .map(word => (word.toLowerCase, 1))       
        .reduceByKey(_ + _)                        

      val outputPath = "Output/wordcount_result"
      wordCounts.saveAsTextFile(outputPath)

      println(s"Word count completed successfully. Results saved in: $outputPath")
    } catch {
      case e: Exception => println(s"Error processing Spark job: ${e.getMessage}")
    } finally {
     
      sc.stop()
    }
  }
}
