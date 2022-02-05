(ns financeiro.core)

(def transacoes
  [{:valor 33.0M :tipo "despesa" :comentario "Almoço"
    :moeda "R$" :data "19/11/2016"}
   {:valor 2700.0M :tipo "receita" :comentario "Bico"
    :moeda "R$" :data "01/12/2016"}
   {:valor 29.0M :tipo "despesa" :comentario "Livro de Clojure"
    :moeda "R$" :data "03/12/2016"}])

(def trasacao-aleatoria {:valor 9.0M})
(def cotacoes
  {:yuan {:cotacao 2.15M :simbolo "¥"}
   :euro {:cotacao 0.28M :simbolo "€"}})

(defn valor-sinalizado [transacao]
  (let [moeda (:moeda transacao "R$")
        valor (:valor transacao)]
    (if (= (:tipo transacao) "despesa")
      (str moeda " -" valor)
      (str moeda " +" valor))))

(defn data-valor [trasacao]
  (str (:data trasacao) " => " (valor-sinalizado trasacao)))

(defn trasacao-em-outra-moeda [moeda trasacao]
  (let [{{cotacao :cotacao simbolo :simbolo} moeda} cotacoes]
    (assoc trasacao :valor (* cotacao (:valor trasacao))
                    :moeda simbolo)))

(def transacao-em-euro (partial trasacao-em-outra-moeda :euro))

(def transacao-em-yuan (partial trasacao-em-outra-moeda :yuan))

(def texto-resumo-em-yuan (comp data-valor trasacao-em-outra-moeda))

(println (valor-sinalizado (first transacoes)))
(println (valor-sinalizado (second transacoes)))
(println (valor-sinalizado trasacao-aleatoria))
(println "_______")
(println (data-valor (first transacoes)))
(println (data-valor (second transacoes)))
(println (data-valor trasacao-aleatoria))
(println "_______")
(println (data-valor (first transacoes)))
(println (data-valor (trasacao-em-outra-moeda :yuan (first transacoes))))
(println "_______")
(println (trasacao-em-outra-moeda :euro (first transacoes)))
(println (trasacao-em-outra-moeda :yuan (first transacoes)))
(println "_______")
(println (transacao-em-euro (first transacoes)))
(println (transacao-em-yuan (first transacoes)))
