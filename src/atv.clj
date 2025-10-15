(ns turma)

(defn classificar-conceito [nota]
  (cond
    (>= nota 90) "A"
    (>= nota 80) "B"
    (>= nota 70) "C"
    (>= nota 60) "D"
    :else "F"))

(defn calcular-desempenho [media]
  (cond
    (>= media 80) "Turma excelente!"
    (>= media 60) "Bom desempenho!"
    :else "É necessário melhorar!"))

(defn calcular-estatisticas [num-alunos]
  (loop [i 1
         soma-notas 0
         aprovados 0
         alunos []]
    (if (> i num-alunos)
      (let [media (/ soma-notas num-alunos)
            desempenho (calcular-desempenho media)]
        {:media media
         :aprovados aprovados
         :desempenho desempenho
         :alunos alunos})
      (do
        (println (str "Nome do aluno " i ":"))
        (let [nome (read-line)
              _ (println "Nota:")
              nota (read-string (read-line))
              conceito (classificar-conceito nota)
              alunos (conj alunos {:nome nome :nota nota :conceito conceito})
              soma-notas (+ soma-notas nota)
              aprovados (if (>= nota 60) (inc aprovados) aprovados)]
          (println (str nome " - Conceito: " conceito))
          (recur (inc i) soma-notas aprovados alunos))))))
(defn iniciar-programa []
  (println "Quantos alunos na turma?")
  (let [num-alunos (read-string (read-line))]
    (let [{:keys [media aprovados desempenho alunos]} (calcular-estatisticas num-alunos)]
      (println (str "\nMédia da turma: " media))
      (println (str "Aprovados: " aprovados))
      (println (str "Desempenho geral: " desempenho)))))

(iniciar-programa)
