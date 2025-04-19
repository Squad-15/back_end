# 🛠️ Fluxo de Trabalho

1. ✅ **Clonar o repositório do GitHub**

   Clona o projeto completo do GitHub para sua máquina:
   ```bash
   git clone https://github.com/Squad-15/back_end.git
   ```

2. 🌿 **Criar uma nova branch para sua tarefa**

   Cria uma nova branch (cópia separada da main) e troca para ela:
   ```bash
   git checkout -b feature/nome-da-tarefa
   ```

3. ✍️ **Fazer alterações no código**

   Faça as implementações da sua funcionalidade ou correção.

4. 🧪 **Testar localmente e validar que está tudo certo**

   Certifique-se de que o código está funcionando antes de subir.

5. 💾 **Adicionar e commitar as mudanças**

   Salva suas alterações no histórico do Git:
   ```bash
   git add .                             # Adiciona todos os arquivos modificados
   git commit -m "feat: descrição clara" # Cria um ponto de salvamento com uma mensagem
   ```

6. 🔄 **Atualizar com as mudanças da `main`**

   Garante que sua branch está atualizada com as últimas mudanças da `main`:
   ```bash
   git pull origin main  # Puxa as últimas atualizações da branch main do GitHub
   ```

7. ⚔️ **Resolver conflitos (se houver)**

   Se houver alterações no mesmo trecho de código, resolva manualmente e faça um novo commit.

8. 📤 **Enviar a branch para o GitHub**

   Sobe sua branch para o repositório remoto:
   ```bash
   git push origin feature/nome-da-tarefa
   ```

9. 🔁 **Criar um Pull Request (PR)**

   Acesse o link abaixo e crie um PR da sua branch para a `main`:  
   [https://github.com/Squad-15/back_end/pulls](https://github.com/Squad-15/back_end/pulls)

10. 👀 **Aguardar a revisão do time**

    Alguém do time revisa e aprova o código.

11. ✅ **Após aprovação, fazer o merge com a `main`**

    Isso pode ser feito direto pelo GitHub, clicando em **"Merge Pull Request"**.

12. 🧹 **Excluir a branch (se não for mais necessária)**

    Você pode excluir a branch pelo GitHub ou pelo terminal:
   ```bash
   git branch -d feature/nome-da-tarefa # Deleta localmente
   ```

```bash
🏁 FIM
```

---

## 🔧 Convenções para mensagens de commit

Use o padrão `tipo: descrição`. Exemplos:

- `feat: adiciona tela de login`
- `fix: corrige bug no botão de logout`
- `docs: atualiza README com instruções`
- `refactor: simplifica função de autenticação`

Tipos comuns:
- `feat`: nova funcionalidade
- `fix`: correção de bug
- `docs`: documentação
- `style`: formatação (sem mudanças de lógica)
- `refactor`: refatoração de código
- `test`: testes adicionados ou corrigidos
- `chore`: tarefas gerais (build, CI, etc)

---

## ✏️ Convenções de nome de branch

- `feature/nome-da-funcionalidade`
- `bugfix/nome-do-bug`
- `hotfix/correção-urgente`

---

## 📌 Dica: mantenha sua branch sempre atualizada com a `main`

Manter sua branch atualizada evita conflitos e garante que você esteja trabalhando com a versão mais recente:

```bash
git checkout main       # Vai para a branch main
git pull origin main    # Baixa as atualizações do GitHub
git checkout sua-branch # Volta para sua branch
git merge main          # Mescla as mudanças da main na sua branch
```

---

> Para mais dicas, veja a [documentação oficial do Git](https://git-scm.com/doc) ou [Guia do GitHub](https://docs.github.com/pt/get-started/quickstart).
