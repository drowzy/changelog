(ns changelog.core
  (:require [me.raynes.conch :refer [programs with-programs let-programs] :as sh]))
(programs git)

(def log-args ["--pretty=format:" "\"%h-%an-%ar-%s\""])

(defn prepare-log-line [line]
  (clojure.string/split (clojure.string/replace line #"\"" "") #"-"))

(defn git-log
  [dir]
  (git "log" (clojure.string/join log-args) {:seq true :dir dir}))

(defn markdown-format
  [[sha1 author time commit-msg]]
  (str "[" sha1 "]" "(http://github.com)" " " "**"author"**" " " "_"time"_" "\n> " commit-msg "\n"))

(defn write-changelog
  [lines]
  (spit "changelog.md" (clojure.string/join "\n" lines)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
