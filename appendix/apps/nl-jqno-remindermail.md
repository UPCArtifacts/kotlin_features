## nl-jqno-remindermail
----
#### Metrics provided by Detekt
* Number of lines of code 341
* Number of Kotlin files: 6
* Cyclomatic complexity: 56
* Cyclomatic complexity by thousands of lines: 309 

----
**6** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#string_template">String Template</a> 
*	<a href="#property_delegation">Property Delegation</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.011958x%5E3%20&plus;-0.580722x%5E2%20&plus;%208.976926x%20&plus;%20-11.284879',))
    * **R_Squared:** 0.97329466
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000846x%5E4%20&plus;%20-0.025266x%5E3%20&plus;-0.045695x%5E2%20&plus;%206.214411x%20&plus;%20-7.615706)
    * **R_Squared:** 0.97855708
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?14.590954%5Clog_%7B3.340977%7D%28x%29%20&plus;%200.146768)
    * **R_Squared:** 0.85557847
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?1.328571x%20&plus;%2011.671429)
    * **R_Squared:** 0.59447013

**Plots** :chart_with_upwards_trend:
-----

![nl-jqno-remindermail T11_3](../plots/nl-jqno-remindermail_type_inference_T11_3.png)
![nl-jqno-remindermail T11_4](../plots/nl-jqno-remindermail_type_inference_T11_4.png)
![nl-jqno-remindermail T6](../plots/nl-jqno-remindermail_type_inference_T6.png)
![nl-jqno-remindermail T1](../plots/nl-jqno-remindermail_type_inference_T1.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.005682x%5E3%20&plus;-0.242895x%5E2%20&plus;%203.309583x%20&plus;%20-1.224149',))
    * **R_Squared:** 0.97205073
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?4.878224%5Clog_%7B3.616254%7D%28x%29%20&plus;%203.166843)
    * **R_Squared:** 0.84773726
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.427068x%20&plus;%206.715789)
    * **R_Squared:** 0.56888939

**Plots** :chart_with_upwards_trend:
-----

![nl-jqno-remindermail T11_3](../plots/nl-jqno-remindermail_lambda_T11_3.png)
![nl-jqno-remindermail T6](../plots/nl-jqno-remindermail_lambda_T6.png)
![nl-jqno-remindermail T1](../plots/nl-jqno-remindermail_lambda_T1.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.000709x%5E4%20&plus;%200.030659x%5E3%20&plus;-0.468331x%5E2%20&plus;%202.956184x%20&plus;%201.602941)
    * **R_Squared:** 0.98556049
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.005117x%5E3%20&plus;-0.166796x%5E2%20&plus;%201.666323x%20&plus;%203.058824',))
    * **R_Squared:** 0.91836124
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.465739%5Clog_%7B3.716867%7D%28x%29%20&plus;%205.387879)
    * **R_Squared:** 0.69701483
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.127451x%20&plus;%206.441176)
    * **R_Squared:** 0.36580087

**Plots** :chart_with_upwards_trend:
-----

![nl-jqno-remindermail T11_4](../plots/nl-jqno-remindermail_safe_call_T11_4.png)
![nl-jqno-remindermail T11_3](../plots/nl-jqno-remindermail_safe_call_T11_3.png)
![nl-jqno-remindermail T6](../plots/nl-jqno-remindermail_safe_call_T6.png)
![nl-jqno-remindermail T1](../plots/nl-jqno-remindermail_safe_call_T1.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B3.892807%7D%7B1%20&plus;%20%5Cepsilon%5E%28-2.035963%28x%20-2.899711%29%29%7D%20&plus;%201.1268)
    * **R_Squared:** 0.98527134
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.000416x%5E4%20&plus;%200.020206x%5E3%20&plus;-0.350942x%5E2%20&plus;%202.556713x%20&plus;%20-1.495098)
    * **R_Squared:** 0.95562053
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.004386x%5E3%20&plus;-0.154025x%5E2%20&plus;%201.670795x%20&plus;%20-0.45098',))
    * **R_Squared:** 0.9266654
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.672619%5Clog_%7B3.718282%7D%28x%29%20&plus;%201.924745)
    * **R_Squared:** 0.72368884
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.143447x%20&plus;%203.137255)
    * **R_Squared:** 0.40692066

**Plots** :chart_with_upwards_trend:
-----

![nl-jqno-remindermail T7](../plots/nl-jqno-remindermail_unsafe_call_T7.png)
![nl-jqno-remindermail T11_4](../plots/nl-jqno-remindermail_unsafe_call_T11_4.png)
![nl-jqno-remindermail T11_3](../plots/nl-jqno-remindermail_unsafe_call_T11_3.png)
![nl-jqno-remindermail T6](../plots/nl-jqno-remindermail_unsafe_call_T6.png)
![nl-jqno-remindermail T1](../plots/nl-jqno-remindermail_unsafe_call_T1.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-44.870612%28x%20-2.498363%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.000193x%5E4%20&plus;%200.008471x%5E3%20&plus;-0.131256x%5E2%20&plus;%200.840471x%20&plus;%200.161765)
    * **R_Squared:** 0.85175439
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.001505x%5E3%20&plus;-0.04902x%5E2%20&plus;%200.488691x%20&plus;%200.558824',))
    * **R_Squared:** 0.8004386
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.979181%5Clog_%7B21.196315%7D%28x%29%20&plus;%201.250407)
    * **R_Squared:** 0.59026347
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.036765x%20&plus;%201.551471)
    * **R_Squared:** 0.3125

**Plots** :chart_with_upwards_trend:
-----

![nl-jqno-remindermail T9](../plots/nl-jqno-remindermail_string_template_T9.png)
![nl-jqno-remindermail T11_4](../plots/nl-jqno-remindermail_string_template_T11_4.png)
![nl-jqno-remindermail T11_3](../plots/nl-jqno-remindermail_string_template_T11_3.png)
![nl-jqno-remindermail T6](../plots/nl-jqno-remindermail_string_template_T6.png)
![nl-jqno-remindermail T1](../plots/nl-jqno-remindermail_string_template_T1.png)
### <a name="property_delegation">Property Delegation</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-44.223239%28x%20-3.499526%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.001075x%5E3%20&plus;-0.039345x%5E2%20&plus;%200.451066x%20&plus;%200.411765',))
    * **R_Squared:** 0.82817337
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.000275%5Clog_%7B12.90966%7D%28x%29%20&plus;%201.042659)
    * **R_Squared:** 0.66854814
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.04644x%20&plus;%201.392157)
    * **R_Squared:** 0.41795666

**Plots** :chart_with_upwards_trend:
-----

![nl-jqno-remindermail T9](../plots/nl-jqno-remindermail_property_delegation_T9.png)
![nl-jqno-remindermail T11_3](../plots/nl-jqno-remindermail_property_delegation_T11_3.png)
![nl-jqno-remindermail T6](../plots/nl-jqno-remindermail_property_delegation_T6.png)
![nl-jqno-remindermail T1](../plots/nl-jqno-remindermail_property_delegation_T1.png)
