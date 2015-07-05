sqlite.db3 - это файл Ѕазы ƒанных, наша SQLite в которой все хранитс€

CreateDB()
—оздает базу (т.е. как раз sqlite.db3) можно вызвать если удалил sqlite.db3

WriteDB()
«аполн€ет базу некими начальными данными

Integer AddWallet(String name, Integer amount) 
ƒобавл€ет новый кошелек, возвращает id кошелька, если кошелек с таким именем уже существует возвращает -1

void DelWallet(Integer idWallet)
”дал€ет кошелек

Integer AddIncome(String name, Integer amount)
ƒобавл€ет категорию доходов, возвращает id категории, если категори€ с таким именем уже существует возвращает -1

void DelIncome(Integer idIncome)
удал€ет категорию доходов

Integer AddExpense(String name, Integer amount)
добавл€ет категорию расходов, возвращает id категории, если категори€ с таким именем уже существует возвращает -1

void DelExpense(Integer idSpend)
удал€ет категорию расходов

void AddIncomeStory(Integer idIncome, Integer idWallet, Integer many)
добавление транзакции дохода

void AddExpenseStory(Integer idExpense, Integer idWallet, Integer many)
добавление транзакции расхода

List<WalletImpl> GetListWallet()
¬ыводит список кошельков

List<WalletImpl> GetListIncome()
выводит список категорий доходов

List<WalletImpl> GetListExpense()
выводит список категорий расходов

