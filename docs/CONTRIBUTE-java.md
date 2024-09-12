Here is an improved version of the **HOW TO CONTRIBUTE** guide:

---

# HOW TO CONTRIBUTE

Thank you for considering contributing to this repository! Here is everything
you need to know to get started.

---

## 🏆 Developer Best Practices

Writing code is a form of communication. To ensure the codebase remains readable
and maintainable, please follow these best practices:

1. **Small Commits**: Follow the KISS (Keep It So Simple) principle. Make small,
   incremental commits. Read more about this
   in [“The Power of Small Commits.”](https://levelup.gitconnected.com/the-power-of-working-in-small-commits-8bae57ecfbda).

2. **Use GitMoji**: To make commit messages more descriptive,
   use [GitMoji](https://gitmoji.dev/) to add emojis that signify the purpose or
   intent of the commit.

3. **One Solution Per PR**: Each Pull Request (PR) should address a single
   solution or feature. If you need to refactor, submit it as a separate PR.

4. **Unit Tests Are Essential**: As part of the Agile Definition of Done, ensure
   that unit tests are included. Whether you write them first (TDD) or before
   the final commit, they are mandatory.

5. **Run Linters**: Ensure your code passes all linting checks.

6. **Static Code Analysis with SonarQube**: Run a SonarQube analysis and address
   its recommendations before submitting a PR.

7. **TDD for Bugs**: When fixing a bug, use Test-Driven Development (TDD). Write
   a unit test that reproduces the issue (red), modify the test to reflect the
   expected behavior (green), and then refactor the code to solve the bug and
   meet the test criteria (blue).

---

## 📝 Git Workflow

### Branches

⚠️ Direct pushes to the `main` and `develop` branches are not allowed. Always
create a new branch and submit a PR for review.

Follow the branch naming conventions below:

- `main`: The main branch containing the latest stable release.
- `develop`: The development branch containing the most recent development
  version.
- `feature/xxx`: For new features.
- `bugfix/xxx`: For bug fixes.
- `hotfix/xxx`: For hotfixes.
- `docs/xxx`: For documentation changes.
- `refactor/xxx`: For code refactoring.
- `test/xxx`: For test-related changes.
- `ci/xxx`: For Continuous Integration (CI) changes.

#### Commit Messages

- Use [GitMoji](https://gitmoji.dev/) to add emojis that describe the purpose of
  your commits.
- Use imperative, present tense for commit messages, e.g., "add" instead of "
  added" or "adds."
    - Examples:
        - `✨ add new feature`
        - `🐛 fix broken tests`
        - `📝 update README`

#### Pull Requests

- Ensure all PR titles follow the same imperative, present tense format as
  commit messages.
- PRs should be concise, focused, and only include changes related to a single
  topic or issue.

---

## 👩‍💻 Development

### Running Code Style Linters

Keeping the code clean and readable is crucial. Follow the quote from Robert C.
Martin (Uncle Bob):

> "The ratio of time spent reading versus writing is well over 10 to 1. We are
> constantly reading old code as part of the effort to write new code. …making
> it
> easy to read makes it easier to write."

To run the linter for Java, execute the following command:

```bash
./gradlew spotlessApply
```

### Running Static Code Analysis

Running static code analysis helps detect potential bugs or code smells before
submitting a PR. Use the following commands to run the analysis:

  ```bash
  ./gradlew sonar
  ```

The report will be accessible at [http://localhost:9000](http://localhost:9000).

You need to have a SonarQube server running locally or via Docker. To run
SonarQube using Docker, execute:

```bash
docker compose -f scripts/docker-compose-sonar.yml up -d
```

For the initial setup of SonarQube, follow the
instructions [here](https://blankfactor.atlassian.net/wiki/spaces/Marqueta/pages/614793231/Developer+s+best+practices#%F0%9F%91%A8%E2%80%8D%F0%9F%92%BB-STATIC-CODE-ANALYSIS-WITH-SONARQUBE).

---

This revised guide provides clearer and more structured information, ensuring a
smooth contribution process while maintaining high-quality code standards.
