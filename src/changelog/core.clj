(ns changelog.core
  (:require
   [changelog.markdown :as markdown]
   [changelog.git :as git]
   [clojure.tools.cli :refer [parse-opts]]))

(def cli-options
  [["-d" "--dir DIR" "Directory" :default "./"]
   ["-f" "--filename FILENAME" "Filename to output formated logs" :default "CLOG.md"]])

(defn usage [options-summary]
  (->> ["Generates a markdown changelog based on commit history"
        ""
        "Usage: changelog [options]"
        ""
        "Options:"
        options-summary
        ""]
       (clojure.string/join \newline)))

(defn prepare-log-line [line]
  (clojure.string/split (clojure.string/replace line #"\"" "") #"-"))

(defn write-file
  [file lines]
  (spit file (clojure.string/join \newline lines)))

(defn -main [& args]
  (let [{:keys [options]} (parse-opts args cli-options)]
    (write-file (:filename options)
     (map markdown/format-line
          (map prepare-log-line (git/log-dir (:dir options)))))))
