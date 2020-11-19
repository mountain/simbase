(defproject mountain/simbase "0.1.0"

    :description "A clojure document similarity server"
    :url "https://github.com/mountain/simbase/"

    :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comments "same as Clojure"}

    :repositories [["bintray" "https://jcenter.bintray.com/"]
                   ["central" "https://repo1.maven.org/maven2/"]]

    :dependencies [[org.clojure/clojure "1.10.1"]
                   [org.yaml/snakeyaml "1.26"]
                   [clj-pid "0.1.2"]
                   [net.sf.trove4j/trove4j "3.0.3"]
                   [org.slf4j/slf4j-api "1.7.30"]
                   [org.slf4j/slf4j-log4j12 "1.7.30"]
                   [org.apache.logging.log4j/log4j-core "2.14.0"]
                   [com.esotericsoftware.kryo/kryo "2.24.0"]
                   ;[com.esotericsoftware/kryo "5.0.0"]
                   [junit "4.13.1" :scope "test"]]
    :source-paths ["src/main/clojure"]
    :java-source-paths ["src/main/java"]
    :resource-paths ["src/main/resources"]

    :test-paths ["src/tests/java/"]
    :test-selectors {:default (complement :integration)
                     :integration :integration
                     :all (constantly true)}
    :compile-path "target/classes"
    :target-path "target/"
    :javac-options ["--release" "15"]
    :omit-source true
    :jvm-opts ["-Xmx1g"]

    :uberjar-name "simbase-standalone.jar"

    :aot :all
    :main simbase.main)
