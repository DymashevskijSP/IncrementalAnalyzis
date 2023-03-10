# IncrementalAnalyzis
## Задача 

анализировать граф вызовов и находить пути до опасных точек программы (pvf)
## Проблема
программы имеют много виртуальных вызовов, и при разной их комбинации можем получать разные уязвимости. Перебирать все варианты подстанновки реализации виртуальных методов очень долго.
## Как решать
DDlog -- язык программирования, реализующий Datalog и позволяющий делать инкрементализированные операции. Таким образом, моэно получать весь граф возможных выховов и анализировать все возможные комбинации одновременно. При этом часть графа, ответственная за Основную программу, без виртуальных методов не меняется, и при изменениях пересчет путей происходит быстрее, чем при полном пересчете путей в графе
## Что готово к текущему моменту
1. Сделана программа на языке DDlog, позволяющая из переданного на вход графа получать пути до уявимых точек. При этом программа выдает на выходе сразу ребра, которые пренадлежат путям до pvf-ов. Таким образом, убираются ребра из графа, которые неинтересно анализировать -- то есть те, которые не нужны для путей до pvf-ов.
2. Программа синтегрирована с кодом на Java, который позволяет получить граф вызовов и по нему получить результат вывода программы на DDlog-е.
3. Программа на Java, которая после получения результатов из DDlog-а, анализирует полученные ребра, и выводит все пути до pvf-ов по очереди.
 
 При этом есть два неприятных сценария:
 1. Циклы -- Если у нас в графе пути до pvf-а есть цикл, то такой путь нам неинтересен.
 2. Пути, ведущие "вникуда": нам интересны только пути до pvfов. 
 С помощью программы на DDlog-е получается убрать все ненужные пути, а программа на джаве на выходе убирает оставшиеся циклы, поскольку они нам неинтересны.
 
 Основное "тонкое место" -- программа на DDlog-е: граф, подающийся на вход имеет очень большие размеры, поэтому программа на DDlog-е должна быть максимально оптимизирована, поскольку ей придется анализировать все ребра и находить из них нужные. Анализ оставшихся ребер производится уже в java, чтобы программа на ddlog-е была максимально оптимизирована.
 
 ## Текущие планы
 1. Добавить генерацию искуственных графов вызовов, чтобы тестировать функциональность и производительность текущего решения, не только на реальных графах, но и на графах поменьше, сгенерированных вручную
 2. Избавление от циклов происходит стандартным методом -- dfs + убрание ребра, если оно приводит к циклу. Но таким образом получаются пути, ведущие "вникуда", от которых мы хотели избавиться. Так как граф, который получается в релультате анализа DDlog-ом очень мал, то это не сильно влияет на производительность, потому что код на Java не тонкое место, но это надо исследовать
 3. полученную программу необходимо внедрить в использование, для этого необходимо разработать структуру, которая бы выводила пути в удобном для дальнейшего анализа формате, для возможности параллельной обработки, где это возможно.
