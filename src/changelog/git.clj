(ns changelog.git
  (:require
   [me.raynes.conch :refer [programs] :as sh]))

(programs git)

(def log-args ["--pretty=format:" "\"%h-%an-%ar-%s\""])
(defn log-dir
  [dir]
  (git "log" (clojure.string/join log-args) {:seq true :dir dir}))
