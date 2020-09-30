## io-trewartha-positional
----
#### Metrics provided by Detekt
* Number of lines of code 1851
* Number of Kotlin files: 32
* Cyclomatic complexity: 283
* Cyclomatic complexity by thousands of lines: 266 

----
**14** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#when_expr">When expression</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#companion_object">Companion Object</a> 
*	<a href="#string_template">String Template</a> 
*	<a href="#func_with_default_value">Function with Default Value</a> 
*	<a href="#singleton">Singleton</a> 
*	<a href="#range_expr">Range Expression</a> 
*	<a href="#data_class">Data Class</a> 
*	<a href="#func_call_with_named_arg">Function call with Named Argument</a> 
*	<a href="#extension_function">Extension Function</a> 
*	<a href="#property_delegation">Property Delegation</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.0008x%5E3%20&plus;-0.192391x%5E2%20&plus;%2012.849215x%20&plus;%20-57.481263',))
    * **R_Squared:** 0.71939126
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?24.721879%5Clog_%7B3.53965%7D%28x%29%20&plus;%2057.184789)
    * **R_Squared:** 0.0812089
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B32.149254%7D%7B1%20&plus;%20%5Cepsilon%5E%28--569.227515%28x%20-67.156991%29%29%7D%20&plus;%20117.835821)
    * **R_Squared:** 0.06248117
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.031831x%20&plus;%20136.059028)
    * **R_Squared:** 0.00036658
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-33.662922x%5E%7B0.999201%7D%20&plus;%20132.989421)
    * **R_Squared:** 8.98e-06

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T11_3](../plots/io-trewartha-positional_type_inference_T11_3.png)
![io-trewartha-positional T6](../plots/io-trewartha-positional_type_inference_T6.png)
![io-trewartha-positional T10](../plots/io-trewartha-positional_type_inference_T10.png)
![io-trewartha-positional T2](../plots/io-trewartha-positional_type_inference_T2.png)
![io-trewartha-positional T5](../plots/io-trewartha-positional_type_inference_T5.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000346x%5E3%20&plus;-0.074321x%5E2%20&plus;%204.578214x%20&plus;%20-29.471274',))
    * **R_Squared:** 0.54324685
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-40.469321%7D%7B1%20&plus;%20%5Cepsilon%5E%28--0.33124%28x%20-24.61536%29%29%7D%20&plus;%2047.192091)
    * **R_Squared:** 0.36675039
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?12.203173%5Clog_%7B3.296542%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.18055322
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.167625x%20&plus;%2028.705188)
    * **R_Squared:** 0.06928856

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T11_3](../plots/io-trewartha-positional_lambda_T11_3.png)
![io-trewartha-positional T7](../plots/io-trewartha-positional_lambda_T7.png)
![io-trewartha-positional T6](../plots/io-trewartha-positional_lambda_T6.png)
![io-trewartha-positional T1](../plots/io-trewartha-positional_lambda_T1.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000193x%5E3%20&plus;-0.047688x%5E2%20&plus;%203.222705x%20&plus;%20-15.318225',))
    * **R_Squared:** 0.64564521
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?3.87182%5Clog_%7B3.404091%7D%28x%29%20&plus;%2018.981139)
    * **R_Squared:** 0.02634145
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.058322x%20&plus;%2035.317361)
    * **R_Squared:** 0.01528462
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-170.717322x%5E%7B0.803448%7D%20&plus;%2031.380597)
    * **R_Squared:** 0.0

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T11_3](../plots/io-trewartha-positional_safe_call_T11_3.png)
![io-trewartha-positional T6](../plots/io-trewartha-positional_safe_call_T6.png)
![io-trewartha-positional T2](../plots/io-trewartha-positional_safe_call_T2.png)
![io-trewartha-positional T5](../plots/io-trewartha-positional_safe_call_T5.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.849819%5Clog_%7B3.71756%7D%28x%29%20&plus;%204.829041)
    * **R_Squared:** 0.17704289
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.016873x%20&plus;%2012.204354)
    * **R_Squared:** 0.01823567

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T6](../plots/io-trewartha-positional_when_expr_T6.png)
![io-trewartha-positional T1](../plots/io-trewartha-positional_when_expr_T1.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?97.121329x%5E%7B1.16509%7D%20&plus;%200.391364)
    * **R_Squared:** 0.34225866
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-6.607439%7D%7B1%20&plus;%20%5Cepsilon%5E%28-30.356692%28x%20-1.044839%29%29%7D%20&plus;%207.34818)
    * **R_Squared:** 0.07444326
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.004244x%20&plus;%200.555556)
    * **R_Squared:** 0.00528017
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B1053.913614%7D%28x%29%20&plus;%200.788991)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T4](../plots/io-trewartha-positional_unsafe_call_T4.png)
![io-trewartha-positional T10](../plots/io-trewartha-positional_unsafe_call_T10.png)
![io-trewartha-positional T1](../plots/io-trewartha-positional_unsafe_call_T1.png)
![io-trewartha-positional T6](../plots/io-trewartha-positional_unsafe_call_T6.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('5.6e-05x%5E3%20&plus;-0.013622x%5E2%20&plus;%200.900154x%20&plus;%20-2.475506',))
    * **R_Squared:** 0.71595155
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.02609x%20&plus;%2011.656604)
    * **R_Squared:** 0.04256221
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.706331%5Clog_%7B3.720271%7D%28x%29%20&plus;%207.786428)
    * **R_Squared:** 0.01060512

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T11_3](../plots/io-trewartha-positional_companion_object_T11_3.png)
![io-trewartha-positional T2](../plots/io-trewartha-positional_companion_object_T2.png)
![io-trewartha-positional T6](../plots/io-trewartha-positional_companion_object_T6.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B5.796156%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.247997%28x%20-15.77239%29%29%7D%20&plus;%206.111892)
    * **R_Squared:** 0.23650233
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.076464%5Clog_%7B3.715309%7D%28x%29%20&plus;%208.029819)
    * **R_Squared:** 0.05611339
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.007431x%20&plus;%2011.709839)
    * **R_Squared:** 0.00740953

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T7](../plots/io-trewartha-positional_string_template_T7.png)
![io-trewartha-positional T6](../plots/io-trewartha-positional_string_template_T6.png)
![io-trewartha-positional T2](../plots/io-trewartha-positional_string_template_T2.png)
### <a name="func_with_default_value">Function with Default Value</a>
----
#### Functions
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-0.87931%7D%7B1%20&plus;%20%5Cepsilon%5E%28-37.225842%28x%20-54.242024%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 0.77840588
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?33.034071x%5E%7B0.986534%7D%20&plus;%20-0.254783)
    * **R_Squared:** 0.4770498
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.010238x%20&plus;%201.123069)
    * **R_Squared:** 0.44172898
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B2104.137857%7D%28x%29%20&plus;%200.544643)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T10](../plots/io-trewartha-positional_func_with_default_value_T10.png)
![io-trewartha-positional T5](../plots/io-trewartha-positional_func_with_default_value_T5.png)
![io-trewartha-positional T2](../plots/io-trewartha-positional_func_with_default_value_T2.png)
![io-trewartha-positional T6](../plots/io-trewartha-positional_func_with_default_value_T6.png)
### <a name="singleton">Singleton</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B0.666667%7D%7B1%20&plus;%20%5Cepsilon%5E%28-38.413917%28x%20-27.190359%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 0.12840467
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.005093x%20&plus;%201.847238)
    * **R_Squared:** 0.05889077
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.118313%5Clog_%7B2146.046523%7D%28x%29%20&plus;%201.464236)
    * **R_Squared:** 0.00035631

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T9](../plots/io-trewartha-positional_singleton_T9.png)
![io-trewartha-positional T2](../plots/io-trewartha-positional_singleton_T2.png)
![io-trewartha-positional T6](../plots/io-trewartha-positional_singleton_T6.png)
### <a name="range_expr">Range Expression</a>
----
#### Functions
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-47.17726%28x%20-66.500022%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.012047x%20&plus;%201.285208)
    * **R_Squared:** 0.74692683
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B2104.137158%7D%28x%29%20&plus;%200.532258)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T10](../plots/io-trewartha-positional_range_expr_T10.png)
![io-trewartha-positional T2](../plots/io-trewartha-positional_range_expr_T2.png)
![io-trewartha-positional T6](../plots/io-trewartha-positional_range_expr_T6.png)
### <a name="data_class">Data Class</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?99.644167x%5E%7B1.138679%7D%20&plus;%200.630226)
    * **R_Squared:** 0.35556673
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.00048x%20&plus;%200.744025)
    * **R_Squared:** 0.00066554
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B8182.748603%7D%28x%29%20&plus;%200.769231)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T4](../plots/io-trewartha-positional_data_class_T4.png)
![io-trewartha-positional T1](../plots/io-trewartha-positional_data_class_T1.png)
![io-trewartha-positional T6](../plots/io-trewartha-positional_data_class_T6.png)
### <a name="func_call_with_named_arg">Function call with Named Argument</a>
----
#### Functions
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-47.400157%28x%20-27.499836%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?15.229941x%5E%7B0.968843%7D%20&plus;%20-0.234588)
    * **R_Squared:** 0.76111642
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.015302x%20&plus;%200.97563)
    * **R_Squared:** 0.65033223
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B1085.724557%7D%28x%29%20&plus;%200.317647)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T10](../plots/io-trewartha-positional_func_call_with_named_arg_T10.png)
![io-trewartha-positional T5](../plots/io-trewartha-positional_func_call_with_named_arg_T5.png)
![io-trewartha-positional T2](../plots/io-trewartha-positional_func_call_with_named_arg_T2.png)
![io-trewartha-positional T6](../plots/io-trewartha-positional_func_call_with_named_arg_T6.png)
### <a name="extension_function">Extension Function</a>
----
#### Functions
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?54.605588x%5E%7B0.963575%7D%20&plus;%20-0.565643)
    * **R_Squared:** 0.88129288
* **Plateau Gradual Decline - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-9.287584%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.066752%28x%20-12.762493%29%29%7D%20&plus;%209.043714)
    * **R_Squared:** 0.88748708
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.062906x%20&plus;%204.503695)
    * **R_Squared:** 0.71800959
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B154.84169%7D%28x%29%20&plus;%201.484211)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T5](../plots/io-trewartha-positional_extension_function_T5.png)
![io-trewartha-positional T8](../plots/io-trewartha-positional_extension_function_T8.png)
![io-trewartha-positional T2](../plots/io-trewartha-positional_extension_function_T2.png)
![io-trewartha-positional T6](../plots/io-trewartha-positional_extension_function_T6.png)
### <a name="property_delegation">Property Delegation</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?50.161533x%5E%7B1.0471%7D%20&plus;%201.194832)
    * **R_Squared:** 0.89047035
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('5.6e-05x%5E3%20&plus;-0.007224x%5E2%20&plus;%200.289132x%20&plus;%20-1.071095',))
    * **R_Squared:** 0.90025446
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.168349x%20&plus;%20-3.981511)
    * **R_Squared:** 0.6044942
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.442677%5Clog_%7B3.704427%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.19565964

**Plots** :chart_with_upwards_trend:
-----

![io-trewartha-positional T4](../plots/io-trewartha-positional_property_delegation_T4.png)
![io-trewartha-positional T11_3](../plots/io-trewartha-positional_property_delegation_T11_3.png)
![io-trewartha-positional T1](../plots/io-trewartha-positional_property_delegation_T1.png)
![io-trewartha-positional T6](../plots/io-trewartha-positional_property_delegation_T6.png)
