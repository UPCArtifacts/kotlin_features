## enjoyingfoss-om
----
#### Metrics provided by Detekt
* Number of lines of code 956
* Number of Kotlin files: 11
* Cyclomatic complexity: 108
* Cyclomatic complexity by thousands of lines: 238 

----
**11** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#when_expr">When expression</a> 
*	<a href="#companion_object">Companion Object</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#string_template">String Template</a> 
*	<a href="#singleton">Singleton</a> 
*	<a href="#func_call_with_named_arg">Function call with Named Argument</a> 
*	<a href="#data_class">Data Class</a> 
*	<a href="#property_delegation">Property Delegation</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?1.506494x%20&plus;%204.714286)
    * **R_Squared:** 0.82967494
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?12.216412%5Clog_%7B3.37149%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.82811689

**Plots** :chart_with_upwards_trend:
-----

![enjoyingfoss-om T1](../plots/enjoyingfoss-om_type_inference_T1.png)
![enjoyingfoss-om T6](../plots/enjoyingfoss-om_type_inference_T6.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-12.861449x%5E%7B1.083883%7D%20&plus;%202.27515)
    * **R_Squared:** 0.91586982
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.588312x%20&plus;%203.480952)
    * **R_Squared:** 0.86822977
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?4.937463%5Clog_%7B3.71526%7D%28x%29%20&plus;%201.822801)
    * **R_Squared:** 0.61642381
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-4.611111%7D%7B1%20&plus;%20%5Cepsilon%5E%28--103.614951%28x%20-3.104048%29%29%7D%20&plus;%2010.611111)
    * **R_Squared:** 0.1781208

**Plots** :chart_with_upwards_trend:
-----

![enjoyingfoss-om T4](../plots/enjoyingfoss-om_lambda_T4.png)
![enjoyingfoss-om T1](../plots/enjoyingfoss-om_lambda_T1.png)
![enjoyingfoss-om T6](../plots/enjoyingfoss-om_lambda_T6.png)
![enjoyingfoss-om T9](../plots/enjoyingfoss-om_lambda_T9.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.000276x%5E4%20&plus;%200.013341x%5E3%20&plus;-0.216321x%5E2%20&plus;%201.370452x%20&plus;%201.729963)
    * **R_Squared:** 0.26264053
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.001177x%5E3%20&plus;-0.041483x%5E2%20&plus;%200.467707x%20&plus;%202.928989',))
    * **R_Squared:** 0.195856
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.671964%5Clog_%7B3.718038%7D%28x%29%20&plus;%203.418044)
    * **R_Squared:** 0.181961
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1045.002593%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.467392%28x%20--12.797994%29%29%7D%20&plus;%20-1040.268135)
    * **R_Squared:** 0.18492215
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.05974x%20&plus;%203.866667)
    * **R_Squared:** 0.14284428

**Plots** :chart_with_upwards_trend:
-----

![enjoyingfoss-om T11_4](../plots/enjoyingfoss-om_safe_call_T11_4.png)
![enjoyingfoss-om T11_3](../plots/enjoyingfoss-om_safe_call_T11_3.png)
![enjoyingfoss-om T6](../plots/enjoyingfoss-om_safe_call_T6.png)
![enjoyingfoss-om T9](../plots/enjoyingfoss-om_safe_call_T9.png)
![enjoyingfoss-om T1](../plots/enjoyingfoss-om_safe_call_T1.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.2968%7D%7B1%20&plus;%20%5Cepsilon%5E%28-1.974428%28x%20-10.562764%29%29%7D%20&plus;%201.008795)
    * **R_Squared:** 0.83040463
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.002909x%5E3%20&plus;0.086792x%5E2%20&plus;%20-0.528314x%20&plus;%201.656966',))
    * **R_Squared:** 0.73145595
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000125x%5E4%20&plus;%20-0.00814x%5E3%20&plus;0.158615x%5E2%20&plus;%20-0.883246x%20&plus;%202.110681)
    * **R_Squared:** 0.73730065
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.158647x%20&plus;%200.484211)
    * **R_Squared:** 0.58624231
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.021949%5Clog_%7B2.720619%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.49266696

**Plots** :chart_with_upwards_trend:
-----

![enjoyingfoss-om T7](../plots/enjoyingfoss-om_when_expr_T7.png)
![enjoyingfoss-om T11_3](../plots/enjoyingfoss-om_when_expr_T11_3.png)
![enjoyingfoss-om T11_4](../plots/enjoyingfoss-om_when_expr_T11_4.png)
![enjoyingfoss-om T1](../plots/enjoyingfoss-om_when_expr_T1.png)
![enjoyingfoss-om T6](../plots/enjoyingfoss-om_when_expr_T6.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.006393x%5E3%20&plus;-0.214587x%5E2%20&plus;%202.323476x%20&plus;%20-2.045954',))
    * **R_Squared:** 0.81468506
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.001144x%5E4%20&plus;%20-0.027929x%5E3%20&plus;0.12455x%5E2%20&plus;%201.097679x%20&plus;%20-0.845654)
    * **R_Squared:** 0.82375838
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?3.041348%5Clog_%7B3.718677%7D%28x%29%20&plus;%200.69036)
    * **R_Squared:** 0.72380015
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.369231x%20&plus;%202.087912)
    * **R_Squared:** 0.53739528

**Plots** :chart_with_upwards_trend:
-----

![enjoyingfoss-om T11_3](../plots/enjoyingfoss-om_companion_object_T11_3.png)
![enjoyingfoss-om T11_4](../plots/enjoyingfoss-om_companion_object_T11_4.png)
![enjoyingfoss-om T6](../plots/enjoyingfoss-om_companion_object_T6.png)
![enjoyingfoss-om T1](../plots/enjoyingfoss-om_companion_object_T1.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000634x%5E4%20&plus;%20-0.024447x%5E3%20&plus;0.271607x%5E2%20&plus;%20-0.656209x%20&plus;%202.198787)
    * **R_Squared:** 0.59888294
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.733333%7D%7B1%20&plus;%20%5Cepsilon%5E%28-63.863322%28x%20-5.138055%29%29%7D%20&plus;%202.0)
    * **R_Squared:** 0.37306843
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.670893%5Clog_%7B3.718282%7D%28x%29%20&plus;%202.218621)
    * **R_Squared:** 0.10844889
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.030075x%20&plus;%202.984211)
    * **R_Squared:** 0.01991734

**Plots** :chart_with_upwards_trend:
-----

![enjoyingfoss-om T11_4](../plots/enjoyingfoss-om_unsafe_call_T11_4.png)
![enjoyingfoss-om T9](../plots/enjoyingfoss-om_unsafe_call_T9.png)
![enjoyingfoss-om T6](../plots/enjoyingfoss-om_unsafe_call_T6.png)
![enjoyingfoss-om T1](../plots/enjoyingfoss-om_unsafe_call_T1.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-1.428572%7D%7B1%20&plus;%20%5Cepsilon%5E%28-40.715575%28x%20-7.756276%29%29%7D%20&plus;%201.428571)
    * **R_Squared:** 0.65789474
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.001538x%5E4%20&plus;%20-0.044418x%5E3%20&plus;0.414983x%5E2%20&plus;%20-1.516084x%20&plus;%202.947552)
    * **R_Squared:** 0.51257266
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.140659x%20&plus;%201.769231)
    * **R_Squared:** 0.4145749
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B210.541359%7D%28x%29%20&plus;%200.714286)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![enjoyingfoss-om T10](../plots/enjoyingfoss-om_string_template_T10.png)
![enjoyingfoss-om T11_4](../plots/enjoyingfoss-om_string_template_T11_4.png)
![enjoyingfoss-om T2](../plots/enjoyingfoss-om_string_template_T2.png)
![enjoyingfoss-om T6](../plots/enjoyingfoss-om_string_template_T6.png)
### <a name="singleton">Singleton</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000311x%5E4%20&plus;%20-0.013433x%5E3%20&plus;0.182961x%5E2%20&plus;%20-0.844266x%20&plus;%201.333039)
    * **R_Squared:** 0.60719814
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.024675x%20&plus;%200.604762)
    * **R_Squared:** 0.10046382
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B2.730852%7D%28x%29%20&plus;%200.333333)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![enjoyingfoss-om T11_4](../plots/enjoyingfoss-om_singleton_T11_4.png)
![enjoyingfoss-om T2](../plots/enjoyingfoss-om_singleton_T2.png)
![enjoyingfoss-om T6](../plots/enjoyingfoss-om_singleton_T6.png)
### <a name="func_call_with_named_arg">Function call with Named Argument</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.009033x%5E4%20&plus;%200.302448x%5E3%20&plus;-3.516608x%5E2%20&plus;%2016.232517x%20&plus;%20-11.030303)
    * **R_Squared:** 0.63601476
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.085664x%5E3%20&plus;-1.791375x%5E2%20&plus;%2011.138112x%20&plus;%20-6.80303',))
    * **R_Squared:** 0.61839725
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?3.738971%5Clog_%7B3.516276%7D%28x%29%20&plus;%206.814155)
    * **R_Squared:** 0.25738534
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.418182x%20&plus;%209.036364)
    * **R_Squared:** 0.10085796

**Plots** :chart_with_upwards_trend:
-----

![enjoyingfoss-om T11_4](../plots/enjoyingfoss-om_func_call_with_named_arg_T11_4.png)
![enjoyingfoss-om T11_3](../plots/enjoyingfoss-om_func_call_with_named_arg_T11_3.png)
![enjoyingfoss-om T6](../plots/enjoyingfoss-om_func_call_with_named_arg_T6.png)
![enjoyingfoss-om T1](../plots/enjoyingfoss-om_func_call_with_named_arg_T1.png)
### <a name="data_class">Data Class</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-45.679228%28x%20-3.498503%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.001748x%5E4%20&plus;%20-0.04157x%5E3%20&plus;0.303613x%5E2%20&plus;%20-0.56216x%20&plus;%201.242424)
    * **R_Squared:** 0.87179487
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.000074%5Clog_%7B6.437826%7D%28x%29%20&plus;%200.872776)
    * **R_Squared:** 0.73390242
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.109091x%20&plus;%201.072727)
    * **R_Squared:** 0.6

**Plots** :chart_with_upwards_trend:
-----

![enjoyingfoss-om T9](../plots/enjoyingfoss-om_data_class_T9.png)
![enjoyingfoss-om T11_4](../plots/enjoyingfoss-om_data_class_T11_4.png)
![enjoyingfoss-om T6](../plots/enjoyingfoss-om_data_class_T6.png)
![enjoyingfoss-om T1](../plots/enjoyingfoss-om_data_class_T1.png)
### <a name="property_delegation">Property Delegation</a>
----
#### Functions
* **Plateau Gradual Decline - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-2.309823%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.367939%28x%20-9.198103%29%29%7D%20&plus;%202.201571)
    * **R_Squared:** 0.92473991
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?34.851142x%5E%7B0.95688%7D%20&plus;%20-2.107182)
    * **R_Squared:** 0.88931748
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.128571x%20&plus;%202.271429)
    * **R_Squared:** 0.87352941
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B83.243283%7D%28x%29%20&plus;%200.857143)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![enjoyingfoss-om T8](../plots/enjoyingfoss-om_property_delegation_T8.png)
![enjoyingfoss-om T5](../plots/enjoyingfoss-om_property_delegation_T5.png)
![enjoyingfoss-om T2](../plots/enjoyingfoss-om_property_delegation_T2.png)
![enjoyingfoss-om T6](../plots/enjoyingfoss-om_property_delegation_T6.png)
