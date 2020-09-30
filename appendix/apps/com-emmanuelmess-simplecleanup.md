## com-emmanuelmess-simplecleanup
----
#### Metrics provided by Detekt
* Number of lines of code 925
* Number of Kotlin files: 19
* Cyclomatic complexity: 139
* Cyclomatic complexity by thousands of lines: 303 

----
**9** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#companion_object">Companion Object</a> 
*	<a href="#string_template">String Template</a> 
*	<a href="#singleton">Singleton</a> 
*	<a href="#data_class">Data Class</a> 
*	<a href="#extension_function">Extension Function</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.002217x%5E4%20&plus;%20-0.121334x%5E3%20&plus;2.004786x%5E2%20&plus;%20-7.037215x%20&plus;%2033.240316)
    * **R_Squared:** 0.95954886
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?25.106221%5Clog_%7B2.958207%7D%28x%29%20&plus;%209.492685)
    * **R_Squared:** 0.82648355
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-43.026316%7D%7B1%20&plus;%20%5Cepsilon%5E%28--291.614577%28x%20-6.724631%29%29%7D%20&plus;%2073.526316)
    * **R_Squared:** 0.77947952
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?2.494615x%20&plus;%2030.77)
    * **R_Squared:** 0.74700256

**Plots** :chart_with_upwards_trend:
-----

![com-emmanuelmess-simplecleanup T11_4](../plots/com-emmanuelmess-simplecleanup_type_inference_T11_4.png)
![com-emmanuelmess-simplecleanup T6](../plots/com-emmanuelmess-simplecleanup_type_inference_T6.png)
![com-emmanuelmess-simplecleanup T9](../plots/com-emmanuelmess-simplecleanup_type_inference_T9.png)
![com-emmanuelmess-simplecleanup T1](../plots/com-emmanuelmess-simplecleanup_type_inference_T1.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000735x%5E4%20&plus;%20-0.040313x%5E3%20&plus;0.6883x%5E2%20&plus;%20-3.026262x%20&plus;%2018.955732)
    * **R_Squared:** 0.95698384
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?7.493727%5Clog_%7B3.386404%7D%28x%29%20&plus;%2010.426044)
    * **R_Squared:** 0.79260931
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.687692x%20&plus;%2015.74)
    * **R_Squared:** 0.77290169

**Plots** :chart_with_upwards_trend:
-----

![com-emmanuelmess-simplecleanup T11_4](../plots/com-emmanuelmess-simplecleanup_lambda_T11_4.png)
![com-emmanuelmess-simplecleanup T6](../plots/com-emmanuelmess-simplecleanup_lambda_T6.png)
![com-emmanuelmess-simplecleanup T1](../plots/com-emmanuelmess-simplecleanup_lambda_T1.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000435x%5E3%20&plus;-0.023251x%5E2%20&plus;%200.396625x%20&plus;%203.86585',))
    * **R_Squared:** 0.88072021
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.759604%5Clog_%7B3.718342%7D%28x%29%20&plus;%204.378022)
    * **R_Squared:** 0.79380519
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.053077x%20&plus;%205.03)
    * **R_Squared:** 0.52021416

**Plots** :chart_with_upwards_trend:
-----

![com-emmanuelmess-simplecleanup T11_3](../plots/com-emmanuelmess-simplecleanup_safe_call_T11_3.png)
![com-emmanuelmess-simplecleanup T6](../plots/com-emmanuelmess-simplecleanup_safe_call_T6.png)
![com-emmanuelmess-simplecleanup T1](../plots/com-emmanuelmess-simplecleanup_safe_call_T1.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.491991%5Clog_%7B3.718514%7D%28x%29%20&plus;%204.604219)
    * **R_Squared:** 0.8116831
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B8809.609286%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.185231%28x%20--41.448585%29%29%7D%20&plus;%20-8801.575338)
    * **R_Squared:** 0.80332996
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.113846x%20&plus;%205.76)
    * **R_Squared:** 0.63438369

**Plots** :chart_with_upwards_trend:
-----

![com-emmanuelmess-simplecleanup T6](../plots/com-emmanuelmess-simplecleanup_unsafe_call_T6.png)
![com-emmanuelmess-simplecleanup T9](../plots/com-emmanuelmess-simplecleanup_unsafe_call_T9.png)
![com-emmanuelmess-simplecleanup T1](../plots/com-emmanuelmess-simplecleanup_unsafe_call_T1.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000173x%5E4%20&plus;%20-0.004523x%5E3%20&plus;0.019081x%5E2%20&plus;%200.234046x%20&plus;%200.623366)
    * **R_Squared:** 0.84130954
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.00205x%5E3%20&plus;-0.062736x%5E2%20&plus;%200.602139x%20&plus;%200.189542',))
    * **R_Squared:** 0.80889182
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.071611%7D%7B1%20&plus;%20%5Cepsilon%5E%28-10.219005%28x%20-3.741742%29%29%7D%20&plus;%200.999818)
    * **R_Squared:** 0.75420173
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.00017%5Clog_%7B8.379573%7D%28x%29%20&plus;%200.937569)
    * **R_Squared:** 0.6404634
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.063983x%20&plus;%201.281046)
    * **R_Squared:** 0.52504098

**Plots** :chart_with_upwards_trend:
-----

![com-emmanuelmess-simplecleanup T11_4](../plots/com-emmanuelmess-simplecleanup_companion_object_T11_4.png)
![com-emmanuelmess-simplecleanup T11_3](../plots/com-emmanuelmess-simplecleanup_companion_object_T11_3.png)
![com-emmanuelmess-simplecleanup T9](../plots/com-emmanuelmess-simplecleanup_companion_object_T9.png)
![com-emmanuelmess-simplecleanup T6](../plots/com-emmanuelmess-simplecleanup_companion_object_T6.png)
![com-emmanuelmess-simplecleanup T1](../plots/com-emmanuelmess-simplecleanup_companion_object_T1.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.000242x%5E4%20&plus;%200.012641x%5E3%20&plus;-0.198043x%5E2%20&plus;%200.670193x%20&plus;%203.630039)
    * **R_Squared:** 0.86249008
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?13.642035x%5E%7B0.870982%7D%20&plus;%20-0.601291)
    * **R_Squared:** 0.76844394
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.193846x%20&plus;%203.64)
    * **R_Squared:** 0.60576923
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B91.190338%7D%28x%29%20&plus;%201.12)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![com-emmanuelmess-simplecleanup T11_4](../plots/com-emmanuelmess-simplecleanup_string_template_T11_4.png)
![com-emmanuelmess-simplecleanup T5](../plots/com-emmanuelmess-simplecleanup_string_template_T5.png)
![com-emmanuelmess-simplecleanup T2](../plots/com-emmanuelmess-simplecleanup_string_template_T2.png)
![com-emmanuelmess-simplecleanup T6](../plots/com-emmanuelmess-simplecleanup_string_template_T6.png)
### <a name="singleton">Singleton</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-46.508214%28x%20-10.499362%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?5.5e-05x%5E4%20&plus;%20-0.003276x%5E3%20&plus;0.062803x%5E2%20&plus;%20-0.353447x%20&plus;%201.451779)
    * **R_Squared:** 0.87813028
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.000436x%5E3%20&plus;0.014695x%5E2%20&plus;%20-0.062399x%20&plus;%201.007115',))
    * **R_Squared:** 0.83717611
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.057692x%20&plus;%200.85)
    * **R_Squared:** 0.72115385
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.088524%5Clog_%7B9.446356%7D%28x%29%20&plus;%200.475356)
    * **R_Squared:** 0.65414564

**Plots** :chart_with_upwards_trend:
-----

![com-emmanuelmess-simplecleanup T9](../plots/com-emmanuelmess-simplecleanup_singleton_T9.png)
![com-emmanuelmess-simplecleanup T11_4](../plots/com-emmanuelmess-simplecleanup_singleton_T11_4.png)
![com-emmanuelmess-simplecleanup T11_3](../plots/com-emmanuelmess-simplecleanup_singleton_T11_3.png)
![com-emmanuelmess-simplecleanup T1](../plots/com-emmanuelmess-simplecleanup_singleton_T1.png)
![com-emmanuelmess-simplecleanup T6](../plots/com-emmanuelmess-simplecleanup_singleton_T6.png)
### <a name="data_class">Data Class</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B3.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-47.1612%28x%20-7.498893%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000182x%5E4%20&plus;%20-0.009481x%5E3%20&plus;0.148532x%5E2%20&plus;%20-0.502645x%20&plus;%201.27747)
    * **R_Squared:** 0.86249008
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.792015%5Clog_%7B3.71809%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.70696447
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.145385x%20&plus;%201.27)
    * **R_Squared:** 0.60576923

**Plots** :chart_with_upwards_trend:
-----

![com-emmanuelmess-simplecleanup T9](../plots/com-emmanuelmess-simplecleanup_data_class_T9.png)
![com-emmanuelmess-simplecleanup T11_4](../plots/com-emmanuelmess-simplecleanup_data_class_T11_4.png)
![com-emmanuelmess-simplecleanup T6](../plots/com-emmanuelmess-simplecleanup_data_class_T6.png)
![com-emmanuelmess-simplecleanup T1](../plots/com-emmanuelmess-simplecleanup_data_class_T1.png)
### <a name="extension_function">Extension Function</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000233x%5E4%20&plus;%20-0.011539x%5E3%20&plus;0.159886x%5E2%20&plus;%20-0.187267x%20&plus;%201.52174)
    * **R_Squared:** 0.87033749
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.915437%5Clog_%7B3.71841%7D%28x%29%20&plus;%200.609422)
    * **R_Squared:** 0.7313472
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.223846x%20&plus;%202.85)
    * **R_Squared:** 0.57870674

**Plots** :chart_with_upwards_trend:
-----

![com-emmanuelmess-simplecleanup T11_4](../plots/com-emmanuelmess-simplecleanup_extension_function_T11_4.png)
![com-emmanuelmess-simplecleanup T6](../plots/com-emmanuelmess-simplecleanup_extension_function_T6.png)
![com-emmanuelmess-simplecleanup T1](../plots/com-emmanuelmess-simplecleanup_extension_function_T1.png)
