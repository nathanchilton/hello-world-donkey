(ns hello-world-donkey.core

  (:require [com.appsflyer.donkey.core :refer [create-donkey create-server]]
            [com.appsflyer.donkey.server :refer [start]]))

(defn byte-array-to-string [byte-array]
  (apply str (map char byte-array)))

(def port 8080)

(->
 (create-donkey)
 (create-server
  {:port   port
   :routes [{:path         "/hello-world-donkey"
             :methods      [:get :post]
             :handler-mode :blocking
           ;;  :consumes     ["text/plain"]
             :produces     ["application/json"]
             :handler      (fn [request]
                             (println (byte-array-to-string (:body request)))
                             {:status 200
                              :body   "{\"greet\":\"Hello, World!\"}"})}]})
 start)

(defn -main
  "I don't do a whole lot."
  []
  (println (str "Server listening on port " port)))
