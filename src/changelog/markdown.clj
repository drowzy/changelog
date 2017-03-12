(ns changelog.markdown)

(defn inline-link [text href] (str "[" text "]" "(" href ")"))
(defn bold [text] (str "**" text "**"))

(defn italic [text] (str "_" text "_"))
(defn blockqoute [text] (str \newline "> " text \newline))

(defn format-line
  [[sha1 author time commit-msg]]
  (str
   (inline-link sha1 "http://github.com") " "
   (bold author) " "
   (italic time)
   (blockqoute commit-msg)))
