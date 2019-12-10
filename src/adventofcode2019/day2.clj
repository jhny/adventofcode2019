(ns adventofcode2019.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))


(defn to-int [x]
  (Integer. x))

(defn split-into-vector [s]
  (into [] (map to-int (str/split s #","))))

(def xs (into [] (map to-int (str/split (slurp "resources/day2/1.txt") #","))))

(defn computer [xs]
  (reduce
    (fn [xs [opt n1 n2 output]]
      (let [opt (nth xs opt)]
        (if (= opt 99)
          (reduced xs)
          (let [number1 (nth xs (nth xs n1))
                number2 (nth xs (nth xs n2))
                output-pos (nth xs output)
                result (cond
                         (= opt 1)
                         (+ number1 number2)
                         (= opt 2)
                         (* number1 number2))]
            (assoc xs output-pos result)))))
    xs
    (partition 4 (range 0 (count xs)))))


(defn part-1 []
  (computer (assoc xs 1 12 2 2)))




(defn part-2
  "Alternatively you create a list of pairs with all possible values.
  This however is more fun :) "
  []
  (loop [noun (rand-int 100)
         verb (rand-int 100)]
    (if (= 19690720 (first (computer (assoc xs 1 noun 2 verb))))
      {:noun   noun
       :verb   verb
       :result (+ (* 100 noun)
                 verb)}
      (recur (rand-int 100) (rand-int 100)))))

(defn part-2-alternative []
  (let [pos (mapcat
              (fn [x]
                (map
                  (fn [y]
                    (list x y))
                  (range 100)))
              (range 100))]
    (reduce
      (fn [_acc [noun verb]]
        (when (= 19690720 (first (computer (assoc xs 1 noun 2 verb))))
          (reduced {:noun   noun
                    :verb   verb
                    :result (+ (* 100 noun)
                              verb)})))
      nil
      pos)))




