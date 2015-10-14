(defproject com.guokr/simbase "0.1.0"

    :description "A clojure document similarity server"
    :url "https://github.com/guokr/simbase/"

    :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comments "same as Clojure"}

    :dependencies [[org.clojure/clojure "1.7.0"]
                   [org.yaml/snakeyaml "1.16"]
                   [clj-pid "0.1.2"]
                   [net.sf.trove4j/trove4j "3.0.3"]
                   [org.slf4j/slf4j-api "1.7.12"]
                   [org.slf4j/slf4j-log4j12 "1.7.12"]
                   [log4j/log4j "1.2.17"]
                   [com.esotericsoftware.kryo/kryo "2.24.0"]
                   [junit "4.12" :scope "test"]]
    :source-paths ["src/main/clojure"]
    :java-source-paths ["src/main/java"]
    :resource-paths ["src/main/resources"]

    :test-paths ["src/tests/java/"]
    :test-selectors {:default (complement :integration)
                     :integration :integration
                     :all (constantly true)}
    :compile-path "target/classes"
    :target-path "target/"
    :javac-options ["-target" "1.8" "-source" "1.8" "-Xlint:-options"]
    :jvm-opts ["-Xmx1g"]

    :uberjar-name "simbase-standalone.jar"

    :aot :all
    :main simbase.main)
