# Clojure Donkey Hello World

I wanted to set up a simple HTTP endpoint using the Clojure language and was looking for a simple way to get started.  I found the [Donkey](https://github.com/AppsFlyer/donkey) framework, which looked like a good option, but none of the examples I could find were actually complete.  When I tried to assemble a basic "hello world" app, that I could use as a starting point, I ran into one error after the next.  Usually, the error messages were extremely vague and didn't give me any clue as to what needed to be fixed.  Once the error messages started making a bit of sense, I knew I was making progress.

This repository contains the most simple starting point that I could assemble.  It receives GET/POST requests, prints the request body to the console and returns a JSON object as a response.

## Prerequisites

- Install [leiningen](https://leiningen.org/)
(This requires Java, but if you are interested in setting up a REST/HTTP API endpoint with [Clojure](https://clojure.org/), I think it is safe to assume that you already know that.)

## Usage

This is so simple that you can accomplish it with just a couple of commands and a quick copy+paste operation, to change two of your files to match mine.  Alternatively, you could just clone this repository and start the server.

### The short way

``` zsh
git clone git@github.com:nathanchilton/hello-world-donkey.git
cd hello-world-donkey
lein run -m hello-world-donkey.core
```

### The long way
Instead of cloning this repository, you can just create a simple project and change it to match what I have here.

First, create the project
``` zsh
lein new hello-world-donkey
cd hello-world-donkey
```

Next, you just need to update two files, to match what is shown here:

#### **project.clj**

``` clojure
(defproject hello-world-donkey "0.1.0-SNAPSHOT"
 :description "FIXME: write description"
 :url "http://example.com/FIXME"
 :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
           :url "https://www.eclipse.org/legal/epl-2.0/"}
 :dependencies [[org.clojure/clojure "1.11.1"]
                [com.appsflyer/donkey "0.5.2"]]
 :repl-options {:init-ns hello-world-donkey.core})
```

### **src/hello_world_donkey/core.clj**
``` clojure
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
```

Now, you can run the server with:

``` zsh
lein run -m hello-world-donkey.core
```

## Result

![image](https://user-images.githubusercontent.com/25993088/205692371-4a4b8209-0d37-49bd-afdd-8769c248abae.png)
