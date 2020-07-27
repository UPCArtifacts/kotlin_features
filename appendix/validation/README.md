## Validation

### Precision

We decided to run the tools over the last version of each application from ourdataset (387 applications). As it was not feasible to manually verify each feature instance, we analyzed a sample of them. To achieve a confidence level of 95% and a confidence interval of 10% we manually checked 96 instances for each feature, which were randomly selected.

To get the minimum number of instances to analyze (96), we compute the confidential level by considering as sample size the number of instances of the most frequent feature, which was type inference with 165,667 instances

[More details and files](appendix_precision.md)

### Recall

To measure the recall of our tool, both authors manually analyzed 100 files randomly selected. 
 Then, we executed our tool over this set of files and calculated the recall.
we found a recall of 100% for all features, but *coroutine* (an experimental feature). Our strategy based on keywords could not identify all possible *coroutines*, resulting in a recall of 91%.

[More details and files](appendix_recall.md)