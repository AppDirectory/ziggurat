(require 'cemerick.pomegranate.aether)
(cemerick.pomegranate.aether/register-wagon-factory!
  "http" #(org.apache.maven.wagon.providers.http.HttpWagon.))

(defproject com.gojek.lambda/ziggurat "2.7.2"
  :description "The actor framework for Project Lambda"
  :url "https://github.com/gojektech/ziggurat"
  :license {:name "Apache License, Version 2.0"
            :url  "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[bidi "2.1.4"]
                 [camel-snake-kebab "0.4.0"]
                 [cheshire "5.8.1"]
                 [clonfig "0.2.0"]
                 [clj-http "3.7.0"]
                 [com.cemerick/url "0.1.1"]
                 [tech.gojek/sentry-clj.async "1.0.0"]
                 [com.gojek/metrics-datadog "1.0.0"
                  :exclusions [com.fasterxml.jackson.core/jackson-databind]]
                 [com.novemberain/langohr "5.0.0"]
                 [com.taoensso/carmine "2.17.0"]
                 [medley "0.8.4"]
                 [mount "0.1.10"]
                 [org.apache.kafka/kafka-streams "0.11.0.1" :exclusions [org.slf4j/slf4j-log4j12 log4j]]
                 [org.apache.logging.log4j/log4j-core "2.7"]
                 [org.apache.logging.log4j/log4j-slf4j-impl "2.7"]
                 [org.clojure/clojure "1.9.0"]
                 [prismatic/schema "1.1.9"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [org.flatland/protobuf "0.8.1"]
                 [ring/ring "1.6.3"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [ring/ring-json "0.4.0"]
                 [ring-logger "0.7.7"]
                 [yleisradio/new-reliquary "1.0.0"]]
  :repositories [["releases" {:url           "***REMOVED***"
                              :username      :env/artifactory_username
                              :password      :env/artifactory_password
                              :sign-releases false}]
                 ["Go-jek" "http://artifactory-gojek.golabs.io/artifactory/gojek-release-local"]]
  :jvm-opts ["-server" "-XX:-OmitStackTraceInFastThrow"]
  :profiles {:uberjar {:aot         :all
                       :global-vars {*warn-on-reflection* true}}
             :test    {:jvm-opts ["-Dlog4j.configurationFile=resources/log4j2.test.xml"]}
             :dev     {:plugins      [[lein-githooks "0.1.0"]
                                      [lein-kibit "0.1.6"]
                                      [jonase/eastwood "0.2.6"]]
                       :githooks     {:auto-install true
                                      :pre-commit   ["lein test"]
                                      :pre-push     ["lein kibit"]}}})
