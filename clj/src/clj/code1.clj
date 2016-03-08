(ns clj.code1
  (:require [clj-http.client :as http])
  (:use clojure.repl)
  (:use clojure.pprint))

;; ############## DATA TYPES ####################
; char
\c
; strings
"Hello sailor!"
; integer
31337
; floating point
3.1415
; booolean
true
;nil
nil
; symbols
this-is-a-symbol
; keywords
:this-is-a-keyword
::ns-keyword
; regular expressions
#"^a.*z$"








;; ############## EVALUATION ####################
(function params)










;; ############## Fuctions ####################
; var
(def var 123)

; anonymous function
(fn []
  (println "First function"))

; shorthand for anonymous functions
#(= 1 %)
#(= 1 %1 %3)
(filter #(= (rem % 2) 0) [1 2 3 4 5 6])
; function in var
(def func1 (fn []
             (println "First function")))
(func)

; function in var
(defn func2
  "This is documentation string for function func2"
  [world]
  (println "Hello" world))
(func2 "World")


(doc func2)
(apropos #"func")
(source println)


(defn tax-amount
  ([amount]
   (tax-amount amount 35))
  ([amount rate]
   (Math/round (double (* amount (/ rate 100))))))



;; ############## DATA STRUCTURES ####################
; list - sequential
(1 2 3)
(list 1 2 3)
; vector - sequential, random access
[1 2 3]
[1 2 :three "four"]
(vector 1 2 3)
; hashmap - associative
{:a 1 :b 2 :c 3}
(hash-map :a 1 :b 2 :c 3)
{:a 1, :b 2, :c 3}
{"a" 1 "b" 2 "c" 3}
{[1 2] 1 "b" 2 "c" 3}
; set - order not guaranteed
#{:a :b :c}
(hash-set :a :b :c)










;;  ############## CHANGES ####################
(def hmap {:a 1 :b 2 :c 3})
(assoc hmap :b 4)
(println hmap)



(let [mhmap (assoc hmap :b 4)]
  (println mhmap))







;;  ############## COLLECTION ABSTRACTIONS ####################
(first '(1 2 3))
(first [1 2 3])
(first {:a 1 :b 2 :c 3})
(first #{:a :b :c})
(first "abcd")
(seq {:a 1 :b 2 :c 3})
(contains? {:a 1 :b 2 :c 3} :o)
(contains? [1 2 3] 2)
(contains? #{:a :b :c} :b)
(#{:a :b :c} :b)
(filter #{:a :c :t} [:a :a :b :d :c :g :t])








;;  ############## COMPLEX DATA STRUCTURES ####################
;{
;"first-name": "Stojan",
;"last-name" : "Jakotyc",
;"age":38,
;"phones": {"home"},
;....................
;}
(def person {
             :first-name "Stojan"
             :last-name  "Jakotyc"
             :age        38
             :phones     {:home "111 111 111" :work "222 222 222" :secret "333 333 333"}
             :adresses   [{
                           :street "Na hrebenech"
                           :city   "Praha"
                           :zip    "120 00"
                           }
                          {
                           :street "Kafci hory"
                           :city   "Praha"
                           :zip    "120 00"
                           }]
             })

(get person :age)
(get person :phones)
(:phones person)
(first (get person :phones))
(rest (get person :phones))
(get-in person [:phones :work])
(get-in person [:phones :work2] "default")
(assoc-in person [:phones :work2] "999 999 999")








;;  ############## HOMOICONICITY AND MACROS ####################
(map inc [1 2 3 4 5])


(defmacro infix
  "Use this macro when you pine for the notation of your childhood"
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))

(infix (2 + 3))
(macroexpand '(infix (1 + 1)))







;;  ############## OBJECTS VS DATA ####################
(def http-params {:headers       {"X-API-Key", "foobar"}
         :client-params {"name" "Manny"}
         :accept        :json})
(http/get "http://api.bileto.zone/status" http-params)


(def resp (http/get "http://api.bileto.zone/status" http-params))
(:status resp)
(select-keys resp [:status :body])







;;  ############## FUNCTIONAL PROGRAMMING ####################
(defn make-adder [x]
  (fn [y]
    (+ x y)))
(def add5 (make-adder 5))
(add5 10)

(defn addxy [x y]
  (+ x y))
(addxy 1 3)
(def add7 (partial addxy 7))
(add7 10)


; apply
(vector [1 2 3])
(apply vector [1 2 3])
;(vector 1 2 3)

; comp
(def concat-and-reverse (comp (partial apply str) reverse str))
;(def concat-and-reverse (apply str (reverse (str "hello" "clojuredocs"))))
(concat-and-reverse "hello" "clojuredocs")
; threading macro
(->
  (str "hello" "clojuredocs")
  (reverse)
  (#(apply str %)))











;;  ############## DESTRUCTURING ####################
(def obj {:a 1 :b 2 :point [11 12] :d {:d1 1 :d2 2 :d3 3}})

(defn dest1 [{point :point}]
  (println point))
(dest1 obj)

(defn dest2 [{[x y] :point :as whole-map}]
  (println x y whole-map))
(dest2 obj)

(defn dest3 [[_ b _ & rest]]
  (println b rest))
(dest3 [1 2 3 4 5])





;;  ############## STATE ####################
; CLJ + CLJS
(def my-atom (atom 0))
(swap! my-atom inc)
(add-watch my-atom :my-key (fn [key atom old-state new-state]
                             (println "Key" key "Atom" atom "OldState" old-state "NewState" new-state)))
(reset! a {:foo "bar"})


; CLJ only
; refs
; agents
; software transactional memory (STM)







;;  ############## OTHER THINGS ####################
(html [:ul
       (for [x (range 1 4)]
         [:li x])])



;;  ############## OTHER THINGS ####################
(frequencies ["Mario" "Mario" "Luigi" "Mario"])
(partition 3 (range 12))