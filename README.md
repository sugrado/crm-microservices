[English👈](README.en-US.md)
# Telko CRM Projesi (Mikroservis)

Bu repo Turkcell GYGY 3.0 Programı kapsamında Pair 2 grubu tarafından geliştirilen Telko CRM projesini içerir. Java programlama dili kullanılarak mikroservis mimarisi uygulanmıştır.


## Geliştirme
- [Conventional Commits](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)
- `@Data` anotasyonu yerine `@Getter`, `@Setter`, ... gibi anotasyonlar kullanmak bize modülerlik ve esneklik sağlamaktadır.
- Package isimleri Oracle dokümanlarına göre lower_snake_case formatında olmalıdır.
- PostgreSQL veritabanı kullanılan servislerde topluluğa göre tablo ve sütun isimleri lower_snake_case formatında olmalıdır.
- `var` anahtar kelimesi mümkün olduğu sürece tercih edilmemelidir. Bunun yerine direkt tip belirtilmelidir. Bunun sebebi ise `var` anahtar kelimesinin okunabilirliği azaltmasıdır.
- Immutable veriler için record tipleri kullanılmalıdır. Örneğin request ve response objeleri.
- Rest API'lerin dünya genelinde kabul görmüş standartları vardır. Şu linkteki makaleler ile bunlara hakim olabilirsiniz: [REST API URI Naming Conventions and Best Practices](https://restfulapi.net/resource-naming/), [Best practices for REST API design](https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/)

## Katkıda Bulunma

1. Projeyi forklayın.
2. Feature branch'inizi şu kod ile oluşturun: `git checkout -b <MyFeature>`
3. Değişikliklerinizi commit'leyin: `git add . && git commit -m '<SemanticCommitType>(<Scope>): <MyFeature>'`
4. İkinci adımda oluşturmuş olduğunuz branch'e push'layın: `git push origin <MyFeature>`
5. Pull request oluşturun.