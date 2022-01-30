(ns financeiro.core)

(def transacoes
  [{:valor 33.0M :tipo "despesa" :comentario "Almoço"
    :moeda "R$" :data "19/11/2016"}
   {:valor 2700.0M :tipo "receita" :comentario "Bico"
    :moeda "R$" :data "01/12/2016"}
   {:valor 29.0M :tipo "despesa" :comentario "Livro de Clojure"
    :moeda "R$" :data "03/12/2016"}])

(def trasacao-aleatoria {:valor 9.0M})
(def cotacoes {:yuan {:cotacao 2.15M :simbolo "¥"}})

(defn valor-sinalizado [transacao]
  (let [moeda (:moeda transacao "R$")
        valor (:valor transacao)]
    (if (= (:tipo transacao) "despesa")
      (str moeda " -" valor)
      (str moeda " +" valor))))

(defn data-valor [trasacao]
  (str (:data trasacao) " => " (valor-sinalizado trasacao)))

(defn trasacao-em-yuan [trasacao]
  (let [yuan (:yuan cotacoes)]
    (assoc trasacao :valor (* (:cotacao yuan)
                              (:valor trasacao))
                    :moeda (:simbolo yuan))))

(def texto-resumo-em-yuan (comp data-valor trasacao-em-yuan))

(println (valor-sinalizado (first transacoes)))
(println (valor-sinalizado (second transacoes)))
(println (valor-sinalizado trasacao-aleatoria))
(println "_______")
(println (data-valor (first transacoes)))
(println (data-valor (second transacoes)))
(println (data-valor trasacao-aleatoria))
(println "_______")
(println (data-valor (first transacoes)))
(println (data-valor (trasacao-em-yuan (first transacoes))))
(println "_______")
(println (map texto-resumo-em-yuan transacoes))
