# Javadoc Generation – CKU Restaurant Manager

## Prerequisites

- JDK 21 installed and `javadoc` available on your PATH.
- Run the command from the **project root** (`finalproject/`).
- All three `.jar` files must be present in the `lib/` directory:
  - `postgresql-42.7.3.jar`
  - `unboundid-ldapsdk.jar`
  - `hamcrest-core-1.3.jar`

## Command

```bash
javadoc -d docs/javadoc \
  -sourcepath src \
  -subpackages jdbc:model:ui \
  -classpath "lib/postgresql-42.7.3.jar:lib/unboundid-ldapsdk.jar:lib/hamcrest-core-1.3.jar" \
  -encoding UTF-8 \
  -charset UTF-8 \
  -author \
  -version \
  -windowtitle "CKU - Restaurant Manager" \
  -doctitle "CKU — Restaurant Manager" \
  src/Main.java
```

## What each flag does

| Flag | Purpose |
| --- | --- |
| `-d docs/javadoc` | Output directory for generated HTML files |
| `-sourcepath src` | Root directory where Java source packages are found |
| `-subpackages jdbc:model:ui` | Recursively documents all classes in these three packages |
| `-classpath ...` | External `.jar` dependencies needed to resolve types at doc-generation time |
| `-encoding UTF-8` | Source file encoding |
| `-charset UTF-8` | Output HTML charset |
| `-author` | Includes `@author` tags in the output |
| `-version` | Includes `@version` tags in the output |
| `-windowtitle` | Browser tab title for the generated HTML |
| `-doctitle` | Main heading shown on the Javadoc index page |
| `src/Main.java` | Explicitly includes the root `Main` class (not inside a package) |

## Viewing the output

After running the command, open the generated documentation in a browser:

```bash
open docs/javadoc/index.html
```

The index page lists all packages and classes with their full API documentation extracted from source Javadoc comments.