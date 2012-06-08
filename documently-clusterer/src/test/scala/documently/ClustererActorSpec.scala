package documently

import actor.{ClustererActor, ClusteredDocuments, DocumentsToCluster, ClusteringError}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.matchers.MustMatchers
import org.scalatest.{BeforeAndAfterAll, WordSpec}
import akka.actor.{Props, ActorSystem}

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

class ClustererActorSpec(_system:ActorSystem) extends TestKit(_system)
  with ImplicitSender
  with WordSpec
  with MustMatchers
  with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    system.shutdown()
  }

  val doc1 = ("TestDocument1", "This is the content for the document 1.")
  val doc2 = ("TestDocument2", "Yet another document to cluster!")
  val doc3 = ("TestDocument3", "Yet another document to cluster!")
  val docs = List(doc1, doc2, doc3)

  val actor = system.actorOf(Props[ClustererActor])


  "Clusterer Actor" should {
    "send back one message with clusters" in {
      actor ! DocumentsToCluster(docs)
      receiveN(1)
    }
    "sends back correct message if he can cluster the documents" in {
      actor ! DocumentsToCluster(docs)
      expectMsgClass(classOf[ClusteredDocuments])
    }
    "sends back error message if he can not cluster the documents" in {
      actor ! DocumentsToCluster(List())
      expectMsgClass(classOf[ClusteringError])
    }
  }


}
