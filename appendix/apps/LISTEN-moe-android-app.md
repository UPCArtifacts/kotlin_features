## LISTEN-moe-android-app
----
#### Metrics provided by Detekt
* Number of lines of code 5781
* Number of Kotlin files: 81
* Cyclomatic complexity: 747
* Cyclomatic complexity by thousands of lines: 264 

----
**13** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#when_expr">When expression</a> 
*	<a href="#companion_object">Companion Object</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#string_template">String Template</a> 
*	<a href="#func_with_default_value">Function with Default Value</a> 
*	<a href="#singleton">Singleton</a> 
*	<a href="#smart_cast">Smart Cast</a> 
*	<a href="#range_expr">Range Expression</a> 
*	<a href="#data_class">Data Class</a> 
*	<a href="#extension_function">Extension Function</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-6.6e-05x%5E4%20&plus;%200.014514x%5E3%20&plus;-1.10423x%5E2%20&plus;%2033.578302x%20&plus;%20-38.052753)
    * **R_Squared:** 0.84892091
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.002352x%5E3%20&plus;-0.382263x%5E2%20&plus;%2018.628205x%20&plus;%2034.094998',))
    * **R_Squared:** 0.75123372
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?57.061415%5Clog_%7B2.738669%7D%28x%29%20&plus;%2070.350701)
    * **R_Squared:** 0.56949056
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?1.308122x%20&plus;%20211.002198)
    * **R_Squared:** 0.24846669

**Plots** :chart_with_upwards_trend:
-----

![LISTEN-moe-android-app T11_4](../plots/LISTEN-moe-android-app_type_inference_T11_4.png)
![LISTEN-moe-android-app T11_3](../plots/LISTEN-moe-android-app_type_inference_T11_3.png)
![LISTEN-moe-android-app T6](../plots/LISTEN-moe-android-app_type_inference_T6.png)
![LISTEN-moe-android-app T1](../plots/LISTEN-moe-android-app_type_inference_T1.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000531x%5E3%20&plus;-0.090198x%5E2%20&plus;%204.743669x%20&plus;%20-8.996141',))
    * **R_Squared:** 0.84698633
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?17.602346%5Clog_%7B2.891561%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.72697089
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.476875x%20&plus;%2036.459341)
    * **R_Squared:** 0.41178846
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-16.687075%7D%7B1%20&plus;%20%5Cepsilon%5E%28--68.838053%28x%20-49.526853%29%29%7D%20&plus;%2067.380952)
    * **R_Squared:** 0.18160945

**Plots** :chart_with_upwards_trend:
-----

![LISTEN-moe-android-app T11_3](../plots/LISTEN-moe-android-app_lambda_T11_3.png)
![LISTEN-moe-android-app T6](../plots/LISTEN-moe-android-app_lambda_T6.png)
![LISTEN-moe-android-app T1](../plots/LISTEN-moe-android-app_lambda_T1.png)
![LISTEN-moe-android-app T9](../plots/LISTEN-moe-android-app_lambda_T9.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-133.661502x%5E%7B1.018194%7D%20&plus;%20-8.364291)
    * **R_Squared:** 0.92900658
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.47175x%20&plus;%20-1.906442)
    * **R_Squared:** 0.89331594
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?7.15921%5Clog_%7B3.484297%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.48183411

**Plots** :chart_with_upwards_trend:
-----

![LISTEN-moe-android-app T4](../plots/LISTEN-moe-android-app_safe_call_T4.png)
![LISTEN-moe-android-app T1](../plots/LISTEN-moe-android-app_safe_call_T1.png)
![LISTEN-moe-android-app T6](../plots/LISTEN-moe-android-app_safe_call_T6.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.661171%5Clog_%7B45.470846%7D%28x%29%20&plus;%2018.452091)
    * **R_Squared:** 0.03022781
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.001306x%20&plus;%2019.102981)
    * **R_Squared:** 0.0011557
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?21920.363995x%5E%7B0.999888%7D%20&plus;%207.437484)
    * **R_Squared:** 0.00114917

**Plots** :chart_with_upwards_trend:
-----

![LISTEN-moe-android-app T6](../plots/LISTEN-moe-android-app_when_expr_T6.png)
![LISTEN-moe-android-app T2](../plots/LISTEN-moe-android-app_when_expr_T2.png)
![LISTEN-moe-android-app T5](../plots/LISTEN-moe-android-app_when_expr_T5.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000132x%5E3%20&plus;-0.020593x%5E2%20&plus;%201.008285x%20&plus;%206.443736',))
    * **R_Squared:** 0.82179786
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?5.090381%5Clog_%7B3.502698%7D%28x%29%20&plus;%206.018899)
    * **R_Squared:** 0.77850116
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-9.357143%7D%7B1%20&plus;%20%5Cepsilon%5E%28--63.70155%28x%20-14.58533%29%29%7D%20&plus;%2021.857143)
    * **R_Squared:** 0.63783468
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.113983x%20&plus;%2015.174359)
    * **R_Squared:** 0.5016673

**Plots** :chart_with_upwards_trend:
-----

![LISTEN-moe-android-app T11_3](../plots/LISTEN-moe-android-app_companion_object_T11_3.png)
![LISTEN-moe-android-app T6](../plots/LISTEN-moe-android-app_companion_object_T6.png)
![LISTEN-moe-android-app T9](../plots/LISTEN-moe-android-app_companion_object_T9.png)
![LISTEN-moe-android-app T1](../plots/LISTEN-moe-android-app_companion_object_T1.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-6.6e-05x%5E4%20&plus;%200.014996x%5E3%20&plus;-1.191365x%5E2%20&plus;%2037.051761x%20&plus;%20-66.287225)
    * **R_Squared:** 0.82204899
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.00294x%5E3%20&plus;-0.475681x%5E2%20&plus;%2022.231789x%20&plus;%205.232554',))
    * **R_Squared:** 0.73858605
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?50.395558%5Clog_%7B2.888979%7D%28x%29%20&plus;%2090.809597)
    * **R_Squared:** 0.34829706
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.782879x%20&plus;%20223.229304)
    * **R_Squared:** 0.07737676

**Plots** :chart_with_upwards_trend:
-----

![LISTEN-moe-android-app T11_4](../plots/LISTEN-moe-android-app_unsafe_call_T11_4.png)
![LISTEN-moe-android-app T11_3](../plots/LISTEN-moe-android-app_unsafe_call_T11_3.png)
![LISTEN-moe-android-app T6](../plots/LISTEN-moe-android-app_unsafe_call_T6.png)
![LISTEN-moe-android-app T1](../plots/LISTEN-moe-android-app_unsafe_call_T1.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('7.5e-05x%5E3%20&plus;-0.011208x%5E2%20&plus;%200.546537x%20&plus;%209.340108',))
    * **R_Squared:** 0.8639126
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.966658%5Clog_%7B3.601237%7D%28x%29%20&plus;%209.072703)
    * **R_Squared:** 0.76011433
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.080169x%20&plus;%2013.721771)
    * **R_Squared:** 0.61282508

**Plots** :chart_with_upwards_trend:
-----

![LISTEN-moe-android-app T11_3](../plots/LISTEN-moe-android-app_string_template_T11_3.png)
![LISTEN-moe-android-app T6](../plots/LISTEN-moe-android-app_string_template_T6.png)
![LISTEN-moe-android-app T1](../plots/LISTEN-moe-android-app_string_template_T1.png)
### <a name="func_with_default_value">Function with Default Value</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.16002%7D%7B1%20&plus;%20%5Cepsilon%5E%28-10.157005%28x%20-26.248656%29%29%7D%20&plus;%201.839999)
    * **R_Squared:** 0.96060221
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.031231%5Clog_%7B2.910217%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.77018131
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.035772x%20&plus;%201.841975)
    * **R_Squared:** 0.66440889

**Plots** :chart_with_upwards_trend:
-----

![LISTEN-moe-android-app T9](../plots/LISTEN-moe-android-app_func_with_default_value_T9.png)
![LISTEN-moe-android-app T6](../plots/LISTEN-moe-android-app_func_with_default_value_T6.png)
![LISTEN-moe-android-app T1](../plots/LISTEN-moe-android-app_func_with_default_value_T1.png)
### <a name="singleton">Singleton</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000103x%5E3%20&plus;-0.015341x%5E2%20&plus;%200.611165x%20&plus;%205.020029',))
    * **R_Squared:** 0.5842663
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-2.194444%7D%7B1%20&plus;%20%5Cepsilon%5E%28-38.080381%28x%20-36.786627%29%29%7D%20&plus;%2011.194444)
    * **R_Squared:** 0.22856604
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.020513x%20&plus;%2010.811722)
    * **R_Squared:** 0.05763416
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.213041%5Clog_%7B3.716442%7D%28x%29%20&plus;%209.292714)
    * **R_Squared:** 0.00441037

**Plots** :chart_with_upwards_trend:
-----

![LISTEN-moe-android-app T11_3](../plots/LISTEN-moe-android-app_singleton_T11_3.png)
![LISTEN-moe-android-app T10](../plots/LISTEN-moe-android-app_singleton_T10.png)
![LISTEN-moe-android-app T2](../plots/LISTEN-moe-android-app_singleton_T2.png)
![LISTEN-moe-android-app T6](../plots/LISTEN-moe-android-app_singleton_T6.png)
### <a name="smart_cast">Smart Cast</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.471545%5Clog_%7B107.287962%7D%28x%29%20&plus;%202.628187)
    * **R_Squared:** 0.17585611
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.001763x%20&plus;%202.902439)
    * **R_Squared:** 0.03614458

**Plots** :chart_with_upwards_trend:
-----

![LISTEN-moe-android-app T6](../plots/LISTEN-moe-android-app_smart_cast_T6.png)
![LISTEN-moe-android-app T1](../plots/LISTEN-moe-android-app_smart_cast_T1.png)
### <a name="range_expr">Range Expression</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-41.819905%28x%20-14.499518%29%29%7D%20&plus;%203.0)
    * **R_Squared:** 1.0
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.418255%5Clog_%7B3.71839%7D%28x%29%20&plus;%202.716907)
    * **R_Squared:** 0.65730925
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.008584x%20&plus;%203.451282)
    * **R_Squared:** 0.39057971

**Plots** :chart_with_upwards_trend:
-----

![LISTEN-moe-android-app T9](../plots/LISTEN-moe-android-app_range_expr_T9.png)
![LISTEN-moe-android-app T6](../plots/LISTEN-moe-android-app_range_expr_T6.png)
![LISTEN-moe-android-app T1](../plots/LISTEN-moe-android-app_range_expr_T1.png)
### <a name="data_class">Data Class</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.07305%5Clog_%7B3.71801%7D%28x%29%20&plus;%203.800924)
    * **R_Squared:** 0.18795156
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.001111x%20&plus;%203.945205)
    * **R_Squared:** 0.04054054

**Plots** :chart_with_upwards_trend:
-----

![LISTEN-moe-android-app T6](../plots/LISTEN-moe-android-app_data_class_T6.png)
![LISTEN-moe-android-app T1](../plots/LISTEN-moe-android-app_data_class_T1.png)
### <a name="extension_function">Extension Function</a>
----
#### Functions
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.011616x%20&plus;%2011.161616)
    * **R_Squared:** 0.24845679
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B4.326861%7D%7B1%20&plus;%20%5Cepsilon%5E%28-13.847958%28x%20-0.898481%29%29%7D%20&plus;%206.524991)
    * **R_Squared:** 0.09465016
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B81.426324%7D%28x%29%20&plus;%2010.836364)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![LISTEN-moe-android-app T2](../plots/LISTEN-moe-android-app_extension_function_T2.png)
![LISTEN-moe-android-app T9](../plots/LISTEN-moe-android-app_extension_function_T9.png)
![LISTEN-moe-android-app T6](../plots/LISTEN-moe-android-app_extension_function_T6.png)
