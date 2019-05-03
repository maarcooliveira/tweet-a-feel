# Tweet a Feel

### Arquitetura
A organização principal dos packages é por feature: main / tweets / analysis / core.

A arquitetura do app é feita com base nos conceitos de Clean Architecture, dividido nas camadas Presentation, Domain e Data.

Na camada de apresentação, o projeto está organizado seguindo MVVM, utilizando o ViewModel do Android Architecture Components.

### Bibliotecas
Foram utilizadas bibliotecas conhecidas em Android para networking (Retrofit, Okhttp), views (Picasso, MaterialDialogs, Google Material Components) e tracking (Firebase, Crashlytics). O projeto utiliza Android X nas dependências.

### Limitações e informações de funcionamento
- O carregamento de tweets está limitado a uma única request de 50 tweets mais recentes em cada conta
- O token do twitter é renovado sempre que a aplicação é reiniciada, e reutilizado durante a execução