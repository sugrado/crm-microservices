# Telko CRM Project with Microservices

This repo includes the **Telko CRM** project developed by the **Pair 2** group within the scope of the **Turkcell GYGY 3.0**
Program. **Microservice** architecture was implemented using the **Java** programming language.

## Development

- [Conventional Commits](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)
- Use `@Getter`, `@Setter`, ... instead of `@Data` annotation in classes. It provides more modularity and flexibility.
- Package names should be in lower snake case. For example **com.turkcell.crm.customer_service.data_access**
- Database table names should be in lower snake case. For example **crm_customers**
- Use type names instead of the `var` keyword. For example **List<Customer> customers** instead of **var customers**
- Use record types instead of classes for immutable data. For example request and response objects.
- Rest api standards in the relevant link must be
  followed. [REST API URI Naming Conventions and Best Practices](https://restfulapi.net/resource-naming/), [Best practices for REST API design](https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/)
- Finally, review your codes periodically.

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b <Feature>/<MyFeature>`
3. Commit your changes: `git add . && git commit -m '<SemanticCommitType>(<Scope>): <MyFeature>'`
4. Push to the branch: `git push origin <Feature>/<MyFeature>`
5. Submit a pull request.