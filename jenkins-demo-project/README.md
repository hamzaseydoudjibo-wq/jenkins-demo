# 🔧 Calculatrice Jenkins — Projet de Démonstration

> **Module :** Tests de Vérification et Validation Logiciel  
> **Exposé :** L'outil Jenkins — Phase 2 : Démonstration Pratique

---

## 📁 Structure du projet

```
jenkins-demo-project/
├── Jenkinsfile                          ← Pipeline CI/CD Jenkins
├── pom.xml                              ← Configuration Maven
└── src/
    ├── main/java/com/demo/calculator/
    │   └── Calculatrice.java            ← Code source (7 méthodes)
    └── test/java/com/demo/calculator/
        └── CalculatriceTest.java        ← 20 tests unitaires JUnit 5
```

---

## 🚀 Étapes de la démonstration (Phase 2)

### 1. Lancer Jenkins avec Docker
```bash
docker run -d \
  --name jenkins-demo \
  -p 8080:8080 \
  -p 50000:50000 \
  -v jenkins_home:/var/jenkins_home \
  jenkins/jenkins:lts
```

### 2. Récupérer le mot de passe initial
```bash
docker exec jenkins-demo cat /var/jenkins_home/secrets/initialAdminPassword
```

### 3. Accéder à l'interface
- Ouvrir : **http://localhost:8080**
- Coller le mot de passe
- Installer les plugins suggérés
- Créer l'utilisateur admin

### 4. Installer les plugins nécessaires
Dans **Gérer Jenkins → Plugins** :
- ✅ JUnit Plugin (résultats des tests)
- ✅ JaCoCo Plugin (couverture de code)
- ✅ Pipeline (déjà installé)

### 5. Mettre le projet sur GitHub
```bash
git init
git add .
git commit -m "Initial commit : projet demo Jenkins"
git remote add origin https://github.com/VOTRE-USERNAME/jenkins-demo.git
git push -u origin main
```

### 6. Créer le Job Pipeline dans Jenkins
1. **Nouveau Job** → nom : `calculatrice-demo` → type : **Pipeline**
2. Section **Pipeline** → choisir : **Pipeline script from SCM**
3. SCM : **Git** → coller l'URL du dépôt GitHub
4. Script Path : `Jenkinsfile`
5. Cliquer **Sauvegarder**

### 7. Lancer le premier Build
- Cliquer **Lancer un Build**
- Observer les étapes s'exécuter en temps réel
- Montrer le rapport de tests JUnit
- Montrer le rapport de couverture JaCoCo

---

## 🧪 Ce que Jenkins va faire automatiquement

| Étape Jenkins | Ce qui se passe | Ce que vous montrez |
|---------------|-----------------|---------------------|
| ✅ Checkout   | Récupère le code Git | Logs avec hash du commit |
| 🔨 Build      | Compile le Java avec Maven | Compilation réussie |
| 🧪 Tests      | Exécute les 20 tests JUnit 5 | **Rapport JUnit : 20/20 ✅** |
| 📈 Couverture | Génère rapport JaCoCo | **Couverture : ~95%** |
| 📦 Package    | Crée le fichier `.jar` | Artefact archivé |
| 🚀 Deploy     | Simulation de déploiement | Message de succès |

---

## 💥 Astuce pour la démo : provoquer un échec !

Pour montrer à vos camarades comment Jenkins détecte les bugs :

**Modifier `Calculatrice.java` ligne 23 :**
```java
// AVANT (correct)
public int additionner(int a, int b) {
    return a + b;
}

// APRÈS (bug intentionnel)
public int additionner(int a, int b) {
    return a - b;   // ← BUG !
}
```

**Puis pousser sur Git :**
```bash
git add .
git commit -m "bug: erreur dans additionner"
git push
```

➡️ Jenkins détecte le bug, marque le build en **ROUGE ❌**  
➡️ Le rapport JUnit montre les tests échoués  
➡️ Corriger le bug, re-pousser → build **VERT ✅**  

**C'est exactement le rôle de la V&V !**

---

## 📊 Résultats attendus

- **Tests :** 20 tests → 20 réussis (100%)
- **Couverture de code :** ~95% (lignes couvertes)
- **Durée du build :** ~30 secondes
- **Artefact :** `calculatrice-jenkins-1.0.0.jar`
