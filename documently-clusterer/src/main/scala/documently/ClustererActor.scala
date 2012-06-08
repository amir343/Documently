package documently

import akka.actor.{ActorLogging, Actor}
import com.jayway.textmining.{NLPFeatureSelection, Cluster, KMeanCluster}
import scalaz._
import Scalaz._


/**
 * Copyright 2012 Amir Moulavi (amir.moulavi@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Amir Moulavi
 */

class ClustererActor extends Actor with ActorLogging {

  log.info("Clusterer Actor is started...")

  def receive = {
    case DocumentsToCluster(docs) =>
      cluster(docs) match {
        case Failure(msg)       => sender ! ClusteringError(msg)
        case Success(clusters)  => sender ! ClusteredDocuments(clusters)
      }

  }

  private def cluster(docs:List[(String, String)]):Validation[String, List[Cluster]] = {
    try {
      val k = scala.math.sqrt(docs.size.toDouble).toInt
      val kMeanCluster = new KMeanCluster(docs, k) with NLPFeatureSelection
      kMeanCluster.doCluster().success
    } catch {
      case e:Exception => e.getMessage.fail
    }
  }

}
