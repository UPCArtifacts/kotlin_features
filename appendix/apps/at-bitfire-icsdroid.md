## at-bitfire-icsdroid
----
#### Metrics provided by Detekt
* Number of lines of code 2496
* Number of Kotlin files: 28
* Cyclomatic complexity: 321
* Cyclomatic complexity by thousands of lines: 264 

----
**12** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#when_expr">When expression</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#companion_object">Companion Object</a> 
*	<a href="#string_template">String Template</a> 
*	<a href="#func_with_default_value">Function with Default Value</a> 
*	<a href="#singleton">Singleton</a> 
*	<a href="#smart_cast">Smart Cast</a> 
*	<a href="#func_call_with_named_arg">Function call with Named Argument</a> 
*	<a href="#destructuring_declaration">Destructuring Declaration</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.00018x%5E3%20&plus;-0.04424x%5E2%20&plus;%203.07385x%20&plus;%20105.757299',))
    * **R_Squared:** 0.51141673
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?14.29932%5Clog_%7B4.435136%7D%28x%29%20&plus;%20117.613142)
    * **R_Squared:** 0.23500134
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.0461x%20&plus;%20152.253922)
    * **R_Squared:** 0.0095008

**Plots** :chart_with_upwards_trend:
-----

![at-bitfire-icsdroid T11_3](../plots/at-bitfire-icsdroid_type_inference_T11_3.png)
![at-bitfire-icsdroid T6](../plots/at-bitfire-icsdroid_type_inference_T6.png)
![at-bitfire-icsdroid T1](../plots/at-bitfire-icsdroid_type_inference_T1.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?11.730779%5Clog_%7B4.091201%7D%28x%29%20&plus;%2030.545241)
    * **R_Squared:** 0.58724942
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.181754x%20&plus;%2050.880719)
    * **R_Squared:** 0.49053039
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-10.526442%7D%7B1%20&plus;%20%5Cepsilon%5E%28--896.2214%28x%20-32.40915%29%29%7D%20&plus;%2065.807692)
    * **R_Squared:** 0.19208462

**Plots** :chart_with_upwards_trend:
-----

![at-bitfire-icsdroid T6](../plots/at-bitfire-icsdroid_lambda_T6.png)
![at-bitfire-icsdroid T1](../plots/at-bitfire-icsdroid_lambda_T1.png)
![at-bitfire-icsdroid T9](../plots/at-bitfire-icsdroid_lambda_T9.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000176x%5E3%20&plus;-0.042734x%5E2%20&plus;%202.712869x%20&plus;%2033.904163',))
    * **R_Squared:** 0.72702047
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B21.24056%7D%7B1%20&plus;%20%5Cepsilon%5E%28--435.119823%28x%20-93.928753%29%29%7D%20&plus;%2051.232559)
    * **R_Squared:** 0.35876729
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.173553x%20&plus;%2077.645752)
    * **R_Squared:** 0.17074421
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-147.785167x%5E%7B0.8278%7D%20&plus;%2065.757353)
    * **R_Squared:** -0.0
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B8203.253244%7D%28x%29%20&plus;%2065.757355)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![at-bitfire-icsdroid T11_3](../plots/at-bitfire-icsdroid_safe_call_T11_3.png)
![at-bitfire-icsdroid T10](../plots/at-bitfire-icsdroid_safe_call_T10.png)
![at-bitfire-icsdroid T2](../plots/at-bitfire-icsdroid_safe_call_T2.png)
![at-bitfire-icsdroid T5](../plots/at-bitfire-icsdroid_safe_call_T5.png)
![at-bitfire-icsdroid T6](../plots/at-bitfire-icsdroid_safe_call_T6.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.724507%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.619984%28x%20-3.764759%29%29%7D%20&plus;%200.883202)
    * **R_Squared:** 0.31878496
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.006244x%20&plus;%203.958815)
    * **R_Squared:** 0.17253322
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B59.685306%7D%28x%29%20&plus;%203.537313)
    * **R_Squared:** 0.0

**Plots** :chart_with_upwards_trend:
-----

![at-bitfire-icsdroid T7](../plots/at-bitfire-icsdroid_when_expr_T7.png)
![at-bitfire-icsdroid T2](../plots/at-bitfire-icsdroid_when_expr_T2.png)
![at-bitfire-icsdroid T6](../plots/at-bitfire-icsdroid_when_expr_T6.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('9.5e-05x%5E3%20&plus;-0.023574x%5E2%20&plus;%201.562431x%20&plus;%20-4.493128',))
    * **R_Squared:** 0.65172029
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-8.595238%7D%7B1%20&plus;%20%5Cepsilon%5E%28--47.849761%28x%20-31.038687%29%29%7D%20&plus;%2018.428571)
    * **R_Squared:** 0.15053749
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.05926x%20&plus;%2020.537255)
    * **R_Squared:** 0.06317396
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-168.36506x%5E%7B0.81982%7D%20&plus;%2016.477941)
    * **R_Squared:** 0.0
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.232472%5Clog_%7B9.003149%7D%28x%29%20&plus;%2014.269674)
    * **R_Squared:** 0.00322757

**Plots** :chart_with_upwards_trend:
-----

![at-bitfire-icsdroid T11_3](../plots/at-bitfire-icsdroid_unsafe_call_T11_3.png)
![at-bitfire-icsdroid T7](../plots/at-bitfire-icsdroid_unsafe_call_T7.png)
![at-bitfire-icsdroid T2](../plots/at-bitfire-icsdroid_unsafe_call_T2.png)
![at-bitfire-icsdroid T5](../plots/at-bitfire-icsdroid_unsafe_call_T5.png)
![at-bitfire-icsdroid T6](../plots/at-bitfire-icsdroid_unsafe_call_T6.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.014984x%20&plus;%2010.974461)
    * **R_Squared:** 0.08995027
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-0.824074%7D%7B1%20&plus;%20%5Cepsilon%5E%28--361.186197%28x%20-27.273065%29%29%7D%20&plus;%2010.12037)
    * **R_Squared:** 0.02866438
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.126752%5Clog_%7B3.715029%7D%28x%29%20&plus;%209.575967)
    * **R_Squared:** 0.00216199

**Plots** :chart_with_upwards_trend:
-----

![at-bitfire-icsdroid T2](../plots/at-bitfire-icsdroid_companion_object_T2.png)
![at-bitfire-icsdroid T9](../plots/at-bitfire-icsdroid_companion_object_T9.png)
![at-bitfire-icsdroid T6](../plots/at-bitfire-icsdroid_companion_object_T6.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B3.840994%7D%7B1%20&plus;%20%5Cepsilon%5E%28-720.68216%28x%20-21.482552%29%29%7D%20&plus;%2026.428571)
    * **R_Squared:** 0.12107526
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.486829%5Clog_%7B3.420642%7D%28x%29%20&plus;%2024.916168)
    * **R_Squared:** 0.08076614
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.013883x%20&plus;%2030.627451)
    * **R_Squared:** 0.01867084

**Plots** :chart_with_upwards_trend:
-----

![at-bitfire-icsdroid T9](../plots/at-bitfire-icsdroid_string_template_T9.png)
![at-bitfire-icsdroid T6](../plots/at-bitfire-icsdroid_string_template_T6.png)
![at-bitfire-icsdroid T2](../plots/at-bitfire-icsdroid_string_template_T2.png)
### <a name="func_with_default_value">Function with Default Value</a>
----
#### Functions
* **Plateau Gradual Decline - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-1.925574%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.420378%28x%20-75.364658%29%29%7D%20&plus;%201.91624)
    * **R_Squared:** 0.93602652
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.020906x%20&plus;%202.459874)
    * **R_Squared:** 0.64244007
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B656.104516%7D%28x%29%20&plus;%201.153226)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![at-bitfire-icsdroid T8](../plots/at-bitfire-icsdroid_func_with_default_value_T8.png)
![at-bitfire-icsdroid T2](../plots/at-bitfire-icsdroid_func_with_default_value_T2.png)
![at-bitfire-icsdroid T6](../plots/at-bitfire-icsdroid_func_with_default_value_T6.png)
### <a name="singleton">Singleton</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.684379%5Clog_%7B4.359533%7D%28x%29%20&plus;%205.478619)
    * **R_Squared:** 0.26623551
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.004303x%20&plus;%207.014052)
    * **R_Squared:** 0.04000347

**Plots** :chart_with_upwards_trend:
-----

![at-bitfire-icsdroid T6](../plots/at-bitfire-icsdroid_singleton_T6.png)
![at-bitfire-icsdroid T1](../plots/at-bitfire-icsdroid_singleton_T1.png)
### <a name="smart_cast">Smart Cast</a>
----
#### Functions
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.037509x%20&plus;%206.35058)
    * **R_Squared:** 0.46855205
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B92.436716%7D%28x%29%20&plus;%203.8)
    * **R_Squared:** -0.0
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B0.812055%7D%7B1%20&plus;%20%5Cepsilon%5E%28-24.359436%28x%20-2.284253%29%29%7D%20&plus;%202.999975)
    * **R_Squared:** 0.00211054

**Plots** :chart_with_upwards_trend:
-----

![at-bitfire-icsdroid T2](../plots/at-bitfire-icsdroid_smart_cast_T2.png)
![at-bitfire-icsdroid T6](../plots/at-bitfire-icsdroid_smart_cast_T6.png)
![at-bitfire-icsdroid T9](../plots/at-bitfire-icsdroid_smart_cast_T9.png)
### <a name="func_call_with_named_arg">Function call with Named Argument</a>
----
#### Functions
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-0.981132%7D%7B1%20&plus;%20%5Cepsilon%5E%28-50.000931%28x%20-71.19254%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 0.96750524
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.011739x%20&plus;%201.314319)
    * **R_Squared:** 0.72508339
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B2104.138851%7D%28x%29%20&plus;%200.580645)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![at-bitfire-icsdroid T10](../plots/at-bitfire-icsdroid_func_call_with_named_arg_T10.png)
![at-bitfire-icsdroid T2](../plots/at-bitfire-icsdroid_func_call_with_named_arg_T2.png)
![at-bitfire-icsdroid T6](../plots/at-bitfire-icsdroid_func_call_with_named_arg_T6.png)
### <a name="destructuring_declaration">Destructuring Declaration</a>
----
#### Functions
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-9.2e-05x%20&plus;%201.997806)
    * **R_Squared:** 0.00139364
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?0.530719x%5E%7B0.999907%7D%20&plus;%200.997807)
    * **R_Squared:** 0.00140296
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B521601.266282%7D%28x%29%20&plus;%201.992)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![at-bitfire-icsdroid T2](../plots/at-bitfire-icsdroid_destructuring_declaration_T2.png)
![at-bitfire-icsdroid T5](../plots/at-bitfire-icsdroid_destructuring_declaration_T5.png)
![at-bitfire-icsdroid T6](../plots/at-bitfire-icsdroid_destructuring_declaration_T6.png)
