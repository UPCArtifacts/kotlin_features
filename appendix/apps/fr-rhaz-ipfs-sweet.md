## fr-rhaz-ipfs-sweet
----
#### Metrics provided by Detekt
* Number of lines of code 323
* Number of Kotlin files: 6
* Cyclomatic complexity: 72
* Cyclomatic complexity by thousands of lines: 404 

----
**10** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#string_template">String Template</a> 
*	<a href="#func_with_default_value">Function with Default Value</a> 
*	<a href="#extension_function">Extension Function</a> 
*	<a href="#inline_func">Inline Function</a> 
*	<a href="#overloaded_op">Overloaded Operator</a> 
*	<a href="#coroutine">Coroutine</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.002032x%5E4%20&plus;%20-0.065553x%5E3%20&plus;0.632256x%5E2%20&plus;%20-1.72663x%20&plus;%2020.375)
    * **R_Squared:** 0.47511442
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.230769%7D%7B1%20&plus;%20%5Cepsilon%5E%28-38.263625%28x%20-3.216725%29%29%7D%20&plus;%2019.0)
    * **R_Squared:** 0.08026756
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.061765x%20&plus;%2020.525)
    * **R_Squared:** 0.02819693
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.046407%5Clog_%7B3.416987%7D%28x%29%20&plus;%2019.927601)
    * **R_Squared:** 0.00028966

**Plots** :chart_with_upwards_trend:
-----

![fr-rhaz-ipfs-sweet T11_4](../plots/fr-rhaz-ipfs-sweet_type_inference_T11_4.png)
![fr-rhaz-ipfs-sweet T9](../plots/fr-rhaz-ipfs-sweet_type_inference_T9.png)
![fr-rhaz-ipfs-sweet T2](../plots/fr-rhaz-ipfs-sweet_type_inference_T2.png)
![fr-rhaz-ipfs-sweet T6](../plots/fr-rhaz-ipfs-sweet_type_inference_T6.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.002612x%5E4%20&plus;%20-0.079786x%5E3%20&plus;0.661474x%5E2%20&plus;%20-0.579657x%20&plus;%2014.625)
    * **R_Squared:** 0.64373769
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.93231%5Clog_%7B3.420931%7D%28x%29%20&plus;%2016.113223)
    * **R_Squared:** 0.18632924
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.132353x%20&plus;%2018.0)
    * **R_Squared:** 0.04812834

**Plots** :chart_with_upwards_trend:
-----

![fr-rhaz-ipfs-sweet T11_4](../plots/fr-rhaz-ipfs-sweet_lambda_T11_4.png)
![fr-rhaz-ipfs-sweet T6](../plots/fr-rhaz-ipfs-sweet_lambda_T6.png)
![fr-rhaz-ipfs-sweet T1](../plots/fr-rhaz-ipfs-sweet_lambda_T1.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.001161x%5E4%20&plus;%20-0.038958x%5E3%20&plus;0.411765x%5E2%20&plus;%20-1.533411x%20&plus;%206.5)
    * **R_Squared:** 0.45186496
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.105882x%20&plus;%205.525)
    * **R_Squared:** 0.24201681
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B43.480065%7D%28x%29%20&plus;%204.625)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![fr-rhaz-ipfs-sweet T11_4](../plots/fr-rhaz-ipfs-sweet_safe_call_T11_4.png)
![fr-rhaz-ipfs-sweet T2](../plots/fr-rhaz-ipfs-sweet_safe_call_T2.png)
![fr-rhaz-ipfs-sweet T6](../plots/fr-rhaz-ipfs-sweet_safe_call_T6.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-0.666667%7D%7B1%20&plus;%20%5Cepsilon%5E%28-32.910942%28x%20-13.301035%29%29%7D%20&plus;%202.0)
    * **R_Squared:** 0.61904762
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000387x%5E4%20&plus;%20-0.012986x%5E3%20&plus;0.137255x%5E2%20&plus;%20-0.511137x%20&plus;%202.5)
    * **R_Squared:** 0.45186496
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.035294x%20&plus;%202.175)
    * **R_Squared:** 0.24201681
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B1061.052986%7D%28x%29%20&plus;%201.875)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![fr-rhaz-ipfs-sweet T10](../plots/fr-rhaz-ipfs-sweet_unsafe_call_T10.png)
![fr-rhaz-ipfs-sweet T11_4](../plots/fr-rhaz-ipfs-sweet_unsafe_call_T11_4.png)
![fr-rhaz-ipfs-sweet T2](../plots/fr-rhaz-ipfs-sweet_unsafe_call_T2.png)
![fr-rhaz-ipfs-sweet T6](../plots/fr-rhaz-ipfs-sweet_unsafe_call_T6.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000822x%5E4%20&plus;%20-0.026284x%5E3%20&plus;0.247501x%5E2%20&plus;%20-0.607746x%20&plus;%202.4375)
    * **R_Squared:** 0.50201929
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.013235x%20&plus;%202.675)
    * **R_Squared:** 0.00750347
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.331736%5Clog_%7B27.70477%7D%28x%29%20&plus;%202.371046)
    * **R_Squared:** 0.01173885

**Plots** :chart_with_upwards_trend:
-----

![fr-rhaz-ipfs-sweet T11_4](../plots/fr-rhaz-ipfs-sweet_string_template_T11_4.png)
![fr-rhaz-ipfs-sweet T2](../plots/fr-rhaz-ipfs-sweet_string_template_T2.png)
![fr-rhaz-ipfs-sweet T6](../plots/fr-rhaz-ipfs-sweet_string_template_T6.png)
### <a name="func_with_default_value">Function with Default Value</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000387x%5E4%20&plus;%20-0.012986x%5E3%20&plus;0.137255x%5E2%20&plus;%20-0.511137x%20&plus;%201.5)
    * **R_Squared:** 0.45186496
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.035294x%20&plus;%201.175)
    * **R_Squared:** 0.24201681
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B19.603909%7D%28x%29%20&plus;%200.875)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![fr-rhaz-ipfs-sweet T11_4](../plots/fr-rhaz-ipfs-sweet_func_with_default_value_T11_4.png)
![fr-rhaz-ipfs-sweet T2](../plots/fr-rhaz-ipfs-sweet_func_with_default_value_T2.png)
![fr-rhaz-ipfs-sweet T6](../plots/fr-rhaz-ipfs-sweet_func_with_default_value_T6.png)
### <a name="extension_function">Extension Function</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.006676x%5E4%20&plus;%20-0.221384x%5E3%20&plus;2.279315x%5E2%20&plus;%20-7.860273x%20&plus;%2023.375)
    * **R_Squared:** 0.44124123
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.485294x%20&plus;%2019.625)
    * **R_Squared:** 0.16341537
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B45.219432%7D%28x%29%20&plus;%2015.5)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![fr-rhaz-ipfs-sweet T11_4](../plots/fr-rhaz-ipfs-sweet_extension_function_T11_4.png)
![fr-rhaz-ipfs-sweet T2](../plots/fr-rhaz-ipfs-sweet_extension_function_T2.png)
![fr-rhaz-ipfs-sweet T6](../plots/fr-rhaz-ipfs-sweet_extension_function_T6.png)
### <a name="inline_func">Inline Function</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.001935x%5E4%20&plus;%20-0.064929x%5E3%20&plus;0.686275x%5E2%20&plus;%20-2.555685x%20&plus;%207.5)
    * **R_Squared:** 0.45186496
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.176471x%20&plus;%205.875)
    * **R_Squared:** 0.24201681
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B43.277894%7D%28x%29%20&plus;%204.375)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![fr-rhaz-ipfs-sweet T11_4](../plots/fr-rhaz-ipfs-sweet_inline_func_T11_4.png)
![fr-rhaz-ipfs-sweet T2](../plots/fr-rhaz-ipfs-sweet_inline_func_T2.png)
![fr-rhaz-ipfs-sweet T6](../plots/fr-rhaz-ipfs-sweet_inline_func_T6.png)
### <a name="overloaded_op">Overloaded Operator</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000387x%5E4%20&plus;%20-0.012986x%5E3%20&plus;0.137255x%5E2%20&plus;%20-0.511137x%20&plus;%201.5)
    * **R_Squared:** 0.45186496
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.035294x%20&plus;%201.175)
    * **R_Squared:** 0.24201681
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B19.603909%7D%28x%29%20&plus;%200.875)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![fr-rhaz-ipfs-sweet T11_4](../plots/fr-rhaz-ipfs-sweet_overloaded_op_T11_4.png)
![fr-rhaz-ipfs-sweet T2](../plots/fr-rhaz-ipfs-sweet_overloaded_op_T2.png)
![fr-rhaz-ipfs-sweet T6](../plots/fr-rhaz-ipfs-sweet_overloaded_op_T6.png)
### <a name="coroutine">Coroutine</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000387x%5E4%20&plus;%20-0.012986x%5E3%20&plus;0.137255x%5E2%20&plus;%20-0.511137x%20&plus;%201.5)
    * **R_Squared:** 0.45186496
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.035294x%20&plus;%201.175)
    * **R_Squared:** 0.24201681
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B19.603909%7D%28x%29%20&plus;%200.875)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![fr-rhaz-ipfs-sweet T11_4](../plots/fr-rhaz-ipfs-sweet_coroutine_T11_4.png)
![fr-rhaz-ipfs-sweet T2](../plots/fr-rhaz-ipfs-sweet_coroutine_T2.png)
![fr-rhaz-ipfs-sweet T6](../plots/fr-rhaz-ipfs-sweet_coroutine_T6.png)
