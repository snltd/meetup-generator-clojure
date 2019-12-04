(ns meetup-generator.lib-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [clojure.spec.alpha :as spec]
            [meetup-generator.lib :refer :all]))

; TODO needs more, and better tests

(deftest test-role
  (testing "A role has at least two words"
    (let [response (role)]
      (= 'java.lang.String (type response))
      (is (>= (count (str/split response #" ")) 2)))))

(deftest test-talker
  (testing "A talker has a given name and a family name"
    (let [response (talker)]
      (= 'java.lang.String (type response))
      (is (= (count (str/split response #" ")) 2)))))

(deftest test-agenda
  (testing "A full agenda is generated"
    (let [response (agenda)]
      (is (= clojure.lang.PersistentArrayMap (type response))))))

(deftest test-company
  (testing "a company name should be something.io"
    (let [response (company)]
      (= 'java.lang.String (type response))
      (is (re-matches #"^[a-z]+.io$" response))
      (is (= (count (str/split response #" ")) 1)))))

(deftest test-something-ops
  (testing "generate a SomethingSomethingOps type string"
    (let [response (something-ops nil)]
      (is (re-matches #"^[A-Z][a-z]+[A-Z]\w+Ops$" response)))))

(deftest test-replace-number
  (testing "generate a string from a template containing %RAND5%"
    (let [response (replace-number "test %RAND5% template")]
      (is (re-matches #"test \d template$" response))))
    (let [response (replace-number "test %RAND5% template %RAND5%")]
      (is (re-matches #"test \d template \d$" response))))

(deftest test-replace-word
  (testing "generate a string from a template containing %WORD%"
    (let [response (replace-word "test %WORD% template")]
      (is (re-matches #"test [A-Z][a-z]+ template$" response)))))

(deftest test-replace-ops
  (testing "generate a string from a template containing %FNOPS%"
    (let [response (replace-ops "test %FNOPS% template")]
      (is (re-matches #"test [A-Z][a-z]+.*Ops template$" response)))))
