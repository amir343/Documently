package documently.config

import com.typesafe.config.{ConfigFactory, Config}


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

class DocumentlySettings(config:Config) {

  config.checkValid(ConfigFactory.defaultReference(), "documently")

  def this() {
    this(ConfigFactory.load())
  }

  val host = config.getString("documently.host")
  val username = config.getString("documently.username")
  val password = config.getString("documently.password")
  val vhost = config.getString("documently.vhost")
  val port = config.getInt("documently.port")


}
