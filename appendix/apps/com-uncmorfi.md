## com-uncmorfi
----
#### Metrics provided by Detekt
* Number of lines of code 3321
* Number of Kotlin files: 44
* Cyclomatic complexity: 477
* Cyclomatic complexity by thousands of lines: 266 

----
**15** features analyzed

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
*	<a href="#coroutine">Coroutine</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000574x%5E3%20&plus;-0.129118x%5E2%20&plus;%208.840854x%20&plus;%2010.236756',))
    * **R_Squared:** 0.86520215
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?41.924681%5Clog_%7B2.792175%7D%28x%29%20&plus;%2014.214447)
    * **R_Squared:** 0.70139753
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.78041x%20&plus;%20122.93015)
    * **R_Squared:** 0.33722631

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T11_3](../plots/com-uncmorfi_type_inference_T11_3.png)
![com-uncmorfi T6](../plots/com-uncmorfi_type_inference_T6.png)
![com-uncmorfi T1](../plots/com-uncmorfi_type_inference_T1.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-458.814219x%5E%7B1.009212%7D%20&plus;%20-72.588864)
    * **R_Squared:** 0.96122396
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?1.08234x%20&plus;%20-15.549625)
    * **R_Squared:** 0.94174635
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?15.914897%5Clog_%7B3.179647%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.42276476

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T4](../plots/com-uncmorfi_lambda_T4.png)
![com-uncmorfi T1](../plots/com-uncmorfi_lambda_T1.png)
![com-uncmorfi T6](../plots/com-uncmorfi_lambda_T6.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.337466x%20&plus;%205.533333)
    * **R_Squared:** 0.89371907
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?8.180832%5Clog_%7B3.435887%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.7322193
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-18.325%7D%7B1%20&plus;%20%5Cepsilon%5E%28--81.13824%28x%20-40.379821%29%29%7D%20&plus;%2030.4)
    * **R_Squared:** 0.67649555

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T1](../plots/com-uncmorfi_safe_call_T1.png)
![com-uncmorfi T6](../plots/com-uncmorfi_safe_call_T6.png)
![com-uncmorfi T9](../plots/com-uncmorfi_safe_call_T9.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-19.098022%7D%7B1%20&plus;%20%5Cepsilon%5E%28--0.089873%28x%20-28.53545%29%29%7D%20&plus;%2020.153355)
    * **R_Squared:** 0.95257607
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?5.449467%5Clog_%7B3.679198%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.75472762
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.155799x%20&plus;%206.282309)
    * **R_Squared:** 0.72315299

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T7](../plots/com-uncmorfi_when_expr_T7.png)
![com-uncmorfi T6](../plots/com-uncmorfi_when_expr_T6.png)
![com-uncmorfi T1](../plots/com-uncmorfi_when_expr_T1.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B6.483279%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.147447%28x%20-19.074534%29%29%7D%20&plus;%203.022812)
    * **R_Squared:** 0.43188947
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.370902%5Clog_%7B3.718282%7D%28x%29%20&plus;%201.646124)
    * **R_Squared:** 0.34180516
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.036987x%20&plus;%206.325143)
    * **R_Squared:** 0.17083702

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T7](../plots/com-uncmorfi_unsafe_call_T7.png)
![com-uncmorfi T6](../plots/com-uncmorfi_unsafe_call_T6.png)
![com-uncmorfi T1](../plots/com-uncmorfi_unsafe_call_T1.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-16.430601%7D%7B1%20&plus;%20%5Cepsilon%5E%28--0.182103%28x%20-16.23102%29%29%7D%20&plus;%2017.91851)
    * **R_Squared:** 0.94300139
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('5.3e-05x%5E3%20&plus;-0.011569x%5E2%20&plus;%200.808809x%20&plus;%200.024446',))
    * **R_Squared:** 0.9126994
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?5.439008%5Clog_%7B3.701952%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.83323261
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.099964x%20&plus;%209.768789)
    * **R_Squared:** 0.53905423

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T7](../plots/com-uncmorfi_companion_object_T7.png)
![com-uncmorfi T11_3](../plots/com-uncmorfi_companion_object_T11_3.png)
![com-uncmorfi T6](../plots/com-uncmorfi_companion_object_T6.png)
![com-uncmorfi T1](../plots/com-uncmorfi_companion_object_T1.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.036527x%20&plus;%209.878706)
    * **R_Squared:** 0.19721477
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B57.468816%7D%28x%29%20&plus;%207.924528)
    * **R_Squared:** 0.0

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T2](../plots/com-uncmorfi_string_template_T2.png)
![com-uncmorfi T6](../plots/com-uncmorfi_string_template_T6.png)
### <a name="func_with_default_value">Function with Default Value</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B4.905002%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.13461%28x%20-48.458285%29%29%7D%20&plus;%204.500895)
    * **R_Squared:** 0.85154973
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.081526x%20&plus;%203.177156)
    * **R_Squared:** 0.82985888
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-278.633576x%5E%7B1.00751%7D%20&plus;%20-4.563323)
    * **R_Squared:** 0.83490363
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.431772%5Clog_%7B3.718038%7D%28x%29%20&plus;%200.107936)
    * **R_Squared:** 0.69919077

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T7](../plots/com-uncmorfi_func_with_default_value_T7.png)
![com-uncmorfi T1](../plots/com-uncmorfi_func_with_default_value_T1.png)
![com-uncmorfi T4](../plots/com-uncmorfi_func_with_default_value_T4.png)
![com-uncmorfi T6](../plots/com-uncmorfi_func_with_default_value_T6.png)
### <a name="singleton">Singleton</a>
----
#### Functions
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?144.628311x%5E%7B0.988075%7D%20&plus;%20-1.454187)
    * **R_Squared:** 0.70073586
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.036933x%20&plus;%203.713983)
    * **R_Squared:** 0.6772533
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-1.817544%7D%7B1%20&plus;%20%5Cepsilon%5E%28-528.253983%28x%20-12.253002%29%29%7D%20&plus;%203.333333)
    * **R_Squared:** 0.17119515
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B401.500575%7D%28x%29%20&plus;%201.719626)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T5](../plots/com-uncmorfi_singleton_T5.png)
![com-uncmorfi T2](../plots/com-uncmorfi_singleton_T2.png)
![com-uncmorfi T10](../plots/com-uncmorfi_singleton_T10.png)
![com-uncmorfi T6](../plots/com-uncmorfi_singleton_T6.png)
### <a name="range_expr">Range Expression</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B3.556793%7D%7B1%20&plus;%20%5Cepsilon%5E%28-1.024546%28x%20-87.416502%29%29%7D%20&plus;%203.068369)
    * **R_Squared:** 0.79065167
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?48.904074x%5E%7B1.023746%7D%20&plus;%202.278207)
    * **R_Squared:** 0.55796881
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.035502x%20&plus;%201.888299)
    * **R_Squared:** 0.49188413
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.384534%5Clog_%7B3.717844%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.36665453

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T7](../plots/com-uncmorfi_range_expr_T7.png)
![com-uncmorfi T4](../plots/com-uncmorfi_range_expr_T4.png)
![com-uncmorfi T1](../plots/com-uncmorfi_range_expr_T1.png)
![com-uncmorfi T6](../plots/com-uncmorfi_range_expr_T6.png)
### <a name="data_class">Data Class</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.135377x%20&plus;%201.885835)
    * **R_Squared:** 0.84000938
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B10.314823%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.058643%28x%20-15.408561%29%29%7D%20&plus;%20-1.161334)
    * **R_Squared:** 0.84222681
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.28787%5Clog_%7B3.721581%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.72734841

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T1](../plots/com-uncmorfi_data_class_T1.png)
![com-uncmorfi T7](../plots/com-uncmorfi_data_class_T7.png)
![com-uncmorfi T6](../plots/com-uncmorfi_data_class_T6.png)
### <a name="func_call_with_named_arg">Function call with Named Argument</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.8141%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.48158%28x%20-52.558683%29%29%7D%20&plus;%202.230183)
    * **R_Squared:** 0.91184176
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?6.680569x%5E%7B1.022397%7D%20&plus;%200.820886)
    * **R_Squared:** 0.79117554
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.049611x%20&plus;%201.205453)
    * **R_Squared:** 0.74259876
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.000127%5Clog_%7B2.901895%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.46160326

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T7](../plots/com-uncmorfi_func_call_with_named_arg_T7.png)
![com-uncmorfi T4](../plots/com-uncmorfi_func_call_with_named_arg_T4.png)
![com-uncmorfi T1](../plots/com-uncmorfi_func_call_with_named_arg_T1.png)
![com-uncmorfi T6](../plots/com-uncmorfi_func_call_with_named_arg_T6.png)
### <a name="extension_function">Extension Function</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.355353x%20&plus;%20-0.926087)
    * **R_Squared:** 0.77225053
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-26.047619%7D%7B1%20&plus;%20%5Cepsilon%5E%28--36.275931%28x%20-32.247851%29%29%7D%20&plus;%2027.047619)
    * **R_Squared:** 0.73923907
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?7.044654%5Clog_%7B3.486378%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.48097297

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T1](../plots/com-uncmorfi_extension_function_T1.png)
![com-uncmorfi T9](../plots/com-uncmorfi_extension_function_T9.png)
![com-uncmorfi T6](../plots/com-uncmorfi_extension_function_T6.png)
### <a name="property_delegation">Property Delegation</a>
----
#### Functions
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-1.260766%7D%7B1%20&plus;%20%5Cepsilon%5E%28-20.087815%28x%20-38.91149%29%29%7D%20&plus;%202.078947)
    * **R_Squared:** 0.64573834
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?30.698762x%5E%7B0.977955%7D%20&plus;%200.531731)
    * **R_Squared:** 0.45767838
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.013351x%20&plus;%202.013793)
    * **R_Squared:** 0.36966993
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B656.106322%7D%28x%29%20&plus;%201.232759)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T10](../plots/com-uncmorfi_property_delegation_T10.png)
![com-uncmorfi T5](../plots/com-uncmorfi_property_delegation_T5.png)
![com-uncmorfi T2](../plots/com-uncmorfi_property_delegation_T2.png)
![com-uncmorfi T6](../plots/com-uncmorfi_property_delegation_T6.png)
### <a name="coroutine">Coroutine</a>
----
#### Functions
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-6.4%7D%7B1%20&plus;%20%5Cepsilon%5E%28-52.591693%28x%20-10.799113%29%29%7D%20&plus;%207.4)
    * **R_Squared:** 0.9507373
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?25.466264x%5E%7B0.917208%7D%20&plus;%200.231084)
    * **R_Squared:** 0.64479339
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.149824x%20&plus;%205.825581)
    * **R_Squared:** 0.47839703
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B91.114726%7D%28x%29%20&plus;%202.454546)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![com-uncmorfi T10](../plots/com-uncmorfi_coroutine_T10.png)
![com-uncmorfi T5](../plots/com-uncmorfi_coroutine_T5.png)
![com-uncmorfi T2](../plots/com-uncmorfi_coroutine_T2.png)
![com-uncmorfi T6](../plots/com-uncmorfi_coroutine_T6.png)
