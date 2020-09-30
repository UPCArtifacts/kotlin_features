## net-codechunk-speedofsound
----
#### Metrics provided by Detekt
* Number of lines of code 1940
* Number of Kotlin files: 13
* Cyclomatic complexity: 195
* Cyclomatic complexity by thousands of lines: 236 

----
**6** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#when_expr">When expression</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#string_template">String Template</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.00076x%5E4%20&plus;%200.03388x%5E3%20&plus;-0.470766x%5E2%20&plus;%201.775148x%20&plus;%20171.130031)
    * **R_Squared:** 0.75053973
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?15.386876x%5E%7B0.872685%7D%20&plus;%20166.846956)
    * **R_Squared:** 0.64927685
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.309023x%20&plus;%20172.694737)
    * **R_Squared:** 0.55245007
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B2119.249132%7D%28x%29%20&plus;%20169.45)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![net-codechunk-speedofsound T11_4](../plots/net-codechunk-speedofsound_type_inference_T11_4.png)
![net-codechunk-speedofsound T5](../plots/net-codechunk-speedofsound_type_inference_T5.png)
![net-codechunk-speedofsound T2](../plots/net-codechunk-speedofsound_type_inference_T2.png)
![net-codechunk-speedofsound T6](../plots/net-codechunk-speedofsound_type_inference_T6.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.130827x%20&plus;%2012.826316)
    * **R_Squared:** 0.86226931
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.443614%5Clog_%7B4.781038%7D%28x%29%20&plus;%2012.246975)
    * **R_Squared:** 0.80931475

**Plots** :chart_with_upwards_trend:
-----

![net-codechunk-speedofsound T1](../plots/net-codechunk-speedofsound_lambda_T1.png)
![net-codechunk-speedofsound T6](../plots/net-codechunk-speedofsound_lambda_T6.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B5.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-47.558468%28x%20-4.502183%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.002257x%5E4%20&plus;%20-0.071087x%5E3%20&plus;0.670257x%5E2%20&plus;%20-1.34431x%20&plus;%201.421245)
    * **R_Squared:** 0.86090793
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?3.269034%5Clog_%7B3.718522%7D%28x%29%20&plus;%200.037014)
    * **R_Squared:** 0.72327509
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.392857x%20&plus;%201.52381)
    * **R_Squared:** 0.58928571

**Plots** :chart_with_upwards_trend:
-----

![net-codechunk-speedofsound T9](../plots/net-codechunk-speedofsound_safe_call_T9.png)
![net-codechunk-speedofsound T11_4](../plots/net-codechunk-speedofsound_safe_call_T11_4.png)
![net-codechunk-speedofsound T6](../plots/net-codechunk-speedofsound_safe_call_T6.png)
![net-codechunk-speedofsound T1](../plots/net-codechunk-speedofsound_safe_call_T1.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-41.31164%28x%20-9.49975%29%29%7D%20&plus;%204.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?7.6e-05x%5E4%20&plus;%20-0.004235x%5E3%20&plus;0.075002x%5E2%20&plus;%20-0.387738x%20&plus;%204.459752)
    * **R_Squared:** 0.86846913
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.001054x%5E3%20&plus;0.031335x%5E2%20&plus;%20-0.171945x%20&plus;%204.183901',))
    * **R_Squared:** 0.85600831
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.074436x%20&plus;%203.768421)
    * **R_Squared:** 0.7443609
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.653985%5Clog_%7B3.718508%7D%28x%29%20&plus;%203.495922)
    * **R_Squared:** 0.62866011

**Plots** :chart_with_upwards_trend:
-----

![net-codechunk-speedofsound T9](../plots/net-codechunk-speedofsound_when_expr_T9.png)
![net-codechunk-speedofsound T11_4](../plots/net-codechunk-speedofsound_when_expr_T11_4.png)
![net-codechunk-speedofsound T11_3](../plots/net-codechunk-speedofsound_when_expr_T11_3.png)
![net-codechunk-speedofsound T1](../plots/net-codechunk-speedofsound_when_expr_T1.png)
![net-codechunk-speedofsound T6](../plots/net-codechunk-speedofsound_when_expr_T6.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.000554x%5E4%20&plus;%200.023427x%5E3%20&plus;-0.294357x%5E2%20&plus;%200.686822x%20&plus;%2053.829721)
    * **R_Squared:** 0.91251919
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?12.698773x%5E%7B0.847849%7D%20&plus;%2048.267377)
    * **R_Squared:** 0.846793
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.3x%20&plus;%2053.6)
    * **R_Squared:** 0.68832662
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B82.733736%7D%28x%29%20&plus;%2050.45)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![net-codechunk-speedofsound T11_4](../plots/net-codechunk-speedofsound_unsafe_call_T11_4.png)
![net-codechunk-speedofsound T5](../plots/net-codechunk-speedofsound_unsafe_call_T5.png)
![net-codechunk-speedofsound T2](../plots/net-codechunk-speedofsound_unsafe_call_T2.png)
![net-codechunk-speedofsound T6](../plots/net-codechunk-speedofsound_unsafe_call_T6.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000288x%5E4%20&plus;%20-0.015046x%5E3%20&plus;0.251939x%5E2%20&plus;%20-1.247692x%20&plus;%209.433437)
    * **R_Squared:** 0.90603797
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.002968x%5E3%20&plus;0.086092x%5E2%20&plus;%20-0.428115x%20&plus;%208.385759',))
    * **R_Squared:** 0.88495437
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.221053x%20&plus;%207.378947)
    * **R_Squared:** 0.77001746
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.9628%5Clog_%7B3.699781%7D%28x%29%20&plus;%206.524198)
    * **R_Squared:** 0.6693761

**Plots** :chart_with_upwards_trend:
-----

![net-codechunk-speedofsound T11_4](../plots/net-codechunk-speedofsound_string_template_T11_4.png)
![net-codechunk-speedofsound T11_3](../plots/net-codechunk-speedofsound_string_template_T11_3.png)
![net-codechunk-speedofsound T1](../plots/net-codechunk-speedofsound_string_template_T1.png)
![net-codechunk-speedofsound T6](../plots/net-codechunk-speedofsound_string_template_T6.png)
