sqlite.db3 - ��� ���� ���� ������, ���� SQLite � ������� ��� ��������

CreateDB()
������� ���� (�.�. ��� ��� sqlite.db3) ����� ������� ���� ������ sqlite.db3

WriteDB()
��������� ���� ������ ���������� �������

Integer AddWallet(String name, Integer amount) 
��������� ����� �������, ���������� id ��������, ���� ������� � ����� ������ ��� ���������� ���������� -1

void DelWallet(Integer idWallet)
������� �������

Integer AddIncome(String name, Integer amount)
��������� ��������� �������, ���������� id ���������, ���� ��������� � ����� ������ ��� ���������� ���������� -1

void DelIncome(Integer idIncome)
������� ��������� �������

Integer AddExpense(String name, Integer amount)
��������� ��������� ��������, ���������� id ���������, ���� ��������� � ����� ������ ��� ���������� ���������� -1

void DelExpense(Integer idSpend)
������� ��������� ��������

void AddIncomeStory(Integer idIncome, Integer idWallet, Integer many)
���������� ���������� ������

void AddExpenseStory(Integer idExpense, Integer idWallet, Integer many)
���������� ���������� �������

List<WalletImpl> GetListWallet()
������� ������ ���������

List<WalletImpl> GetListIncome()
������� ������ ��������� �������

List<WalletImpl> GetListExpense()
������� ������ ��������� ��������

